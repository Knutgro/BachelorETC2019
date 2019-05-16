package com.etc.trader.repository;

import com.etc.trader.model.VehicleAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the VehicleAlbum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleAlbumRepository extends JpaRepository<VehicleAlbum, Long> {
    Optional<VehicleAlbum> findOneByVehicle_id(Long id);

}
