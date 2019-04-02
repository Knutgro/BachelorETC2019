package com.etc.trader.rest;

import com.etc.trader.model.Model;
import com.etc.trader.repository.ModelRepository;
import com.etc.trader.rest.errors.BadRequestAlertException;
import com.etc.trader.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Model.
 */
@RestController
@RequestMapping("/api")
public class ModelResource {

    private static final String ENTITY_NAME = "model";

    private final ModelRepository modelRepository;

    public ModelResource(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    /**
     * POST  /models : Create a new model.
     *
     * @param model the model to create
     * @return the ResponseEntity with status 201 (Created) and with body the new model, or with status 400 (Bad Request) if the model has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/models")
    public ResponseEntity<Model> createModel(@RequestBody Model model) throws URISyntaxException {
        if (model.getId() != null) {
            throw new BadRequestAlertException("A new model cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Model result = modelRepository.save(model);
        return ResponseEntity.created(new URI("/api/models/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /models : Updates an existing model.
     *
     * @param model the model to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated model,
     * or with status 400 (Bad Request) if the model is not valid,
     * or with status 500 (Internal Server Error) if the model couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/models")
    public ResponseEntity<Model> updateModel(@RequestBody Model model) throws URISyntaxException {
        if (model.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Model result = modelRepository.save(model);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, model.getId().toString()))
            .body(result);
    }

    /**
     * GET  /models : get all the models.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of models in body
     */
    @GetMapping("/models")
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    /**
     * GET  /models/:id : get the "id" model.
     *
     * @param id the id of the model to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the model, or with status 404 (Not Found)
     */
    @GetMapping("/models/{id}")
    public ResponseEntity<Model> getModel(@PathVariable Long id) {
        Optional<Model> model = modelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(model);
    }

    /**
     * DELETE  /models/:id : delete the "id" model.
     *
     * @param id the id of the model to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/models/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        modelRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
