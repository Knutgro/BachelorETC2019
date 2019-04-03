package etc.trader.server.rest;


import etc.trader.server.model.Listing;
import etc.trader.server.repository.ListingRepository;
import etc.trader.server.rest.errors.BadRequestAlertException;
import etc.trader.server.rest.util.HeaderUtil;
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
 * REST controller for managing Listing.
 */
@RestController
@RequestMapping("/api")
public class ListingResource {

    private final Logger log = LoggerFactory.getLogger(ListingResource.class);

    private static final String ENTITY_NAME = "listing";

    private final ListingRepository listingRepository;

    public ListingResource(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
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
        log.debug("REST request to save Listing : {}", listing);
        if (listing.getId() != null) {
            throw new BadRequestAlertException("A new listing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Listing result = listingRepository.save(listing);
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
        log.debug("REST request to update Listing : {}", listing);
        if (listing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Listing result = listingRepository.save(listing);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, listing.getId().toString()))
            .body(result);
    }

    /**
     * GET  /listings : get all the listings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of listings in body
     */
    @GetMapping("/listings")
    public List<Listing> getAllListings() {
        log.debug("REST request to get all Listings");
        return listingRepository.findAll();
    }

    /**
     * GET  /listings/:id : get the "id" listing.
     *
     * @param id the id of the listing to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the listing, or with status 404 (Not Found)
     */
    @GetMapping("/listings/{id}")
    public ResponseEntity<Listing> getListing(@PathVariable Long id) {
        log.debug("REST request to get Listing : {}", id);
        Optional<Listing> listing = listingRepository.findById(id);
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
        log.debug("REST request to delete Listing : {}", id);
        listingRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
