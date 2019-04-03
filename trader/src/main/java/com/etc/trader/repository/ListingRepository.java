package com.etc.trader.repository;

import com.etc.trader.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Listing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

}
