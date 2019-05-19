package com.etc.trader.rest;

import com.etc.trader.model.Listing;
import com.etc.trader.rest.errors.BadRequestAlertException;
import com.etc.trader.rest.util.HeaderUtil;
import com.etc.trader.rest.util.PaginationUtil;
import com.etc.trader.rest.xml.ListingXML;
import com.etc.trader.service.ListingService;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Listing.
 */
@RestController
@RequestMapping("/api")
public class ListingResource {


    private static final String ENTITY_NAME = "listing";

    private final ListingService listingService;

    public ListingResource(ListingService listingService) {
        this.listingService = listingService;
    }

    /**
     * POST  /listings : Create a new listing.
     *
     * @param listing the listing to create
     * @return the ResponseEntity with status 201 (Created) and with body the new listing, or with status 400 (Bad Request) if the listing has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/listings")
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing) throws URISyntaxException {
        if (listing.getId() != null) {
            throw new BadRequestAlertException("A new listing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Listing result = listingService.save(listing);
        return ResponseEntity.created(new URI("/api/listings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /listings : Updates an existing listing.
     *
     * @param listing the listing to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated listing,
     * or with status 400 (Bad Request) if the listing is not valid,
     * or with status 500 (Internal Server Error) if the listing couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/listings")
    public ResponseEntity<Listing> updateListing(@RequestBody Listing listing) throws URISyntaxException {
        if (listing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Listing result = listingService.save(listing);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, listing.getId().toString()))
            .body(result);
    }

    /**
     * GET  /listings : get all the listings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of listings in body
     */
    @GetMapping("/listings")
    public ResponseEntity<List<Listing>> getAllListings(Pageable pageable) {
        Page<Listing> page = listingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/listings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /listings/:id : get the "id" listing.
     *
     * @param id the id of the listing to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the listing, or with status 404 (Not Found)
     */
    @GetMapping("/listings/{id}")
    public ResponseEntity<Listing> getListing(@PathVariable Long id) {
        Optional<Listing> listing = listingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listing);
    }

    /**
     * DELETE  /listings/:id : delete the "id" listing.
     *
     * @param id the id of the listing to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/listings/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        listingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/listing-finn")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendToFinn(@RequestBody ListingXML listingXML) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(ListingXML.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(listingXML, sw);
        String xmlString = sw.toString();

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost("FINN.NO api adresse");
        StringEntity input = null;
        try {
            input = new StringEntity(xmlString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert input != null;
        input.setContentType("text/xml");
        postRequest.setEntity(input);
        try {
            client.execute(postRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
