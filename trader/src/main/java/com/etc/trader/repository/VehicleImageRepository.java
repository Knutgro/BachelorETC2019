package com.etc.trader.repository;

import com.etc.trader.model.VehicleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the VehicleImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleImageRepository extends JpaRepository<VehicleImage, Long> {
    Optional<VehicleImage> findOneByVehicle_id(Long id);
    List<VehicleImage> findAllByVehicle_id(Long id);

}
