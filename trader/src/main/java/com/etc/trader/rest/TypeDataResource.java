package com.etc.trader.rest;

import com.etc.trader.model.TypeData;
import com.etc.trader.repository.TypeDataRepository;
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
 * REST controller for managing TypeData.
 */
@RestController
@RequestMapping("/api")
public class TypeDataResource {

    private static final String ENTITY_NAME = "typeData";

    private final TypeDataRepository typeDataRepository;

    public TypeDataResource(TypeDataRepository typeDataRepository) {
        this.typeDataRepository = typeDataRepository;
    }

    /**
     * POST  /type-data : Create a new typeData.
     *
     * @param typeData the typeData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeData, or with status 400 (Bad Request) if the typeData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-data")
    public ResponseEntity<TypeData> createTypeData(@RequestBody TypeData typeData) throws URISyntaxException {
        if (typeData.getId() != null) {
            throw new BadRequestAlertException("A new typeData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeData result = typeDataRepository.save(typeData);
        return ResponseEntity.created(new URI("/api/type-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-data : Updates an existing typeData.
     *
     * @param typeData the typeData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeData,
     * or with status 400 (Bad Request) if the typeData is not valid,
     * or with status 500 (Internal Server Error) if the typeData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-data")
    public ResponseEntity<TypeData> updateTypeData(@RequestBody TypeData typeData) throws URISyntaxException {
        if (typeData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeData result = typeDataRepository.save(typeData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-data : get all the typeData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of typeData in body
     */
    @GetMapping("/type-data")
    public List<TypeData> getAllTypeData() {
        return typeDataRepository.findAll();
    }

    /**
     * GET  /type-data/:id : get the "id" typeData.
     *
     * @param id the id of the typeData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeData, or with status 404 (Not Found)
     */
    @GetMapping("/type-data/{id}")
    public ResponseEntity<TypeData> getTypeData(@PathVariable Long id) {
        Optional<TypeData> typeData = typeDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeData);
    }

    /**
     * DELETE  /type-data/:id : delete the "id" typeData.
     *
     * @param id the id of the typeData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-data/{id}")
    public ResponseEntity<Void> deleteTypeData(@PathVariable Long id) {
        typeDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
