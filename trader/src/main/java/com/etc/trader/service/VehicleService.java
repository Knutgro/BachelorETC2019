package com.etc.trader.service;

import com.etc.trader.model.Vehicle;
import com.etc.trader.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Vehicle.
 */
@Service
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Save a vehicle.
     *
     * @param vehicle the entity to save
     * @return the persisted entity
     */
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    /**
     * Get all the vehicles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Vehicle> findAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }



    /**
     *  get all the vehicles where Listing is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Vehicle> findAllWhereListingIsNull() {
        return StreamSupport
            .stream(vehicleRepository.findAll().spliterator(), false)
            .filter(vehicle -> vehicle.getListing() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one vehicle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Vehicle> findOne(Long id) {
        return vehicleRepository.findById(id);
    }

    /**
     * Delete the vehicle by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> findByCompany(Long id) {
        return vehicleRepository.findAllByCompany_Id(id);

    }
}
