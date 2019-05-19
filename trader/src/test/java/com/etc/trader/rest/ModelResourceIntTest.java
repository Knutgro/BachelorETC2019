package com.etc.trader.rest;

import com.etc.trader.TraderApplication;
import com.etc.trader.model.Model;
import com.etc.trader.repository.ModelRepository;

import com.etc.trader.rest.errors.ExceptionTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.etc.trader.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ModelResource REST controller.
 *
 * @see ModelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TraderApplication.class)
public class ModelResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Qualifier("mvcValidator")
    @Autowired
    private Validator validator;

    private MockMvc restModelMockMvc;

    private Model model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModelResource modelResource = new ModelResource(modelRepository);
        this.restModelMockMvc = MockMvcBuilders.standaloneSetup(modelResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Model createEntity(EntityManager em) {
        Model model = new Model()
            .name(DEFAULT_NAME)
            .year(DEFAULT_YEAR);
        return model;
    }

    @Before
    public void initTest() {
        model = createEntity(em);
    }

    @Test
    @Transactional
    public void createModel() throws Exception {
        int databaseSizeBeforeCreate = modelRepository.findAll().size();

        // Create the Model
        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isCreated());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeCreate + 1);
        Model testModel = modelList.get(modelList.size() - 1);
        assertThat(testModel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testModel.getYear()).isEqualTo(DEFAULT_YEAR);
    }

    @Test
    @Transactional
    public void createModelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modelRepository.findAll().size();

        // Create the Model with an existing ID
        model.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModelMockMvc.perform(post("/api/models")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllModels() throws Exception {
        // Initialize the database
        modelRepository.saveAndFlush(model);

        // Get all the modelList
        restModelMockMvc.perform(get("/api/models?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(model.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)));
    }
    
    @Test
    @Transactional
    public void getModel() throws Exception {
        // Initialize the database
        modelRepository.saveAndFlush(model);

        // Get the model
        restModelMockMvc.perform(get("/api/models/{id}", model.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(model.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR));
    }

    @Test
    @Transactional
    public void getNonExistingModel() throws Exception {
        // Get the model
        restModelMockMvc.perform(get("/api/models/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModel() throws Exception {
        // Initialize the database
        modelRepository.saveAndFlush(model);

        int databaseSizeBeforeUpdate = modelRepository.findAll().size();

        // Update the model
        Model updatedModel = modelRepository.findById(model.getId()).get();
        // Disconnect from session so that the updates on updatedModel are not directly saved in db
        em.detach(updatedModel);
        updatedModel
            .name(UPDATED_NAME)
            .year(UPDATED_YEAR);

        restModelMockMvc.perform(put("/api/models")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedModel)))
            .andExpect(status().isOk());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeUpdate);
        Model testModel = modelList.get(modelList.size() - 1);
        assertThat(testModel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testModel.getYear()).isEqualTo(UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void updateNonExistingModel() throws Exception {
        int databaseSizeBeforeUpdate = modelRepository.findAll().size();

        // Create the Model

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModelMockMvc.perform(put("/api/models")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(model)))
            .andExpect(status().isBadRequest());

        // Validate the Model in the database
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModel() throws Exception {
        // Initialize the database
        modelRepository.saveAndFlush(model);

        int databaseSizeBeforeDelete = modelRepository.findAll().size();

        // Delete the model
        restModelMockMvc.perform(delete("/api/models/{id}", model.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Model> modelList = modelRepository.findAll();
        assertThat(modelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Model.class);
        Model model1 = new Model();
        model1.setId(1L);
        Model model2 = new Model();
        model2.setId(model1.getId());
        assertThat(model1).isEqualTo(model2);
        model2.setId(2L);
        assertThat(model1).isNotEqualTo(model2);
        model1.setId(null);
        assertThat(model1).isNotEqualTo(model2);
    }
}
