package com.etc.trader.rest;

import com.etc.trader.model.Company;
import com.etc.trader.rest.errors.BadRequestAlertException;
import com.etc.trader.rest.util.HeaderUtil;
import com.etc.trader.rest.util.PaginationUtil;
import com.etc.trader.service.CompanyService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api")
public class CompanyResource {

    private static final String ENTITY_NAME = "company";

    private final CompanyService companyService;

    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * POST  /companies : Create a new company.
     *
     * @param company the company to create
     * @return the ResponseEntity with status 201 (Created) and with body the new company, or with status 400 (Bad Request) if the company has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) throws URISyntaxException {
        if (company.getId() != null) {
            throw new BadRequestAlertException("A new company cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Company result = companyService.save(company);
        return ResponseEntity.created(new URI("/api/companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /companies : Updates an existing company.
     *
     * @param company the company to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated company,
     * or with status 400 (Bad Request) if the company is not valid,
     * or with status 500 (Internal Server Error) if the company couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/companies")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) throws URISyntaxException {
        if (company.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Company result = companyService.save(company);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, company.getId().toString()))
            .body(result);
    }

    /**
     * GET  /companies : get all the companies.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of companies in body
     */
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        Page<Company> page;
        if (eagerload) {
            page = companyService.findAllWithEagerRelationships(pageable);
        } else {
            page = companyService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/companies?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /companies/:id : get the "id" company.
     *
     * @param id the id of the company to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the company, or with status 404 (Not Found)
     */
    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Optional<Company> company = companyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(company);
    }

    /**
     * DELETE  /companies/:id : delete the "id" company.
     *
     * @param id the id of the company to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
