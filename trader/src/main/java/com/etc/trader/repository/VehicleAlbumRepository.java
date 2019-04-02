package com.etc.trader.repository;

import com.etc.trader.model.VehicleAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VehicleAlbum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleAlbumRepository extends JpaRepository<VehicleAlbum, Long> {

}
