package com.etc.trader.service;

import com.etc.trader.model.Listing;
import com.etc.trader.repository.ListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Listing.
 */
@Service
@Transactional
public class ListingService {


    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    /**
     * Save a listing.
     *
     * @param listing the entity to save
     * @return the persisted entity
     */
    public Listing save(Listing listing) {
        return listingRepository.save(listing);
    }

    /**
     * Get all the listings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Listing> findAll(Pageable pageable) {
        return listingRepository.findAll(pageable);
    }


    /**
     * Get one listing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Listing> findOne(Long id) {
        return listingRepository.findById(id);
    }

    /**
     * Delete the listing by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        listingRepository.deleteById(id);
    }
}
