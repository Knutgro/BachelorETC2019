package com.etc.trader.rest;

import com.etc.trader.model.Vehicle;
import com.etc.trader.rest.errors.BadRequestAlertException;
import com.etc.trader.rest.util.HeaderUtil;
import com.etc.trader.rest.util.PaginationUtil;
import com.etc.trader.service.VehicleService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Vehicle.
 */
@RestController
@RequestMapping("/api")
public class VehicleResource {

    private static final String ENTITY_NAME = "vehicle";

    private final VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * POST  /vehicles : Create a new vehicle.
     *
     * @param vehicle the vehicle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicle, or with status 400 (Bad Request) if the vehicle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) throws URISyntaxException {
        if (vehicle.getId() != null) {
            throw new BadRequestAlertException("A new vehicle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vehicle result = vehicleService.save(vehicle);
        return ResponseEntity.created(new URI("/api/vehicles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vehicles : Updates an existing vehicle.
     *
     * @param vehicle the vehicle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicle,
     * or with status 400 (Bad Request) if the vehicle is not valid,
     * or with status 500 (Internal Server Error) if the vehicle couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicles")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) throws URISyntaxException {
        if (vehicle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Vehicle result = vehicleService.save(vehicle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehicle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicles : get all the vehicles.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of vehicles in body
     */
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("listing-is-null".equals(filter)) {
            return new ResponseEntity<>(vehicleService.findAllWhereListingIsNull(),
                    HttpStatus.OK);
        }
        Page<Vehicle> page = vehicleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vehicles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vehicles/:id : get the "id" vehicle.
     *
     * @param id the id of the vehicle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicle, or with status 404 (Not Found)
     */
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehicle);
    }

    /**
     * DELETE  /vehicles/:id : delete the "id" vehicle.
     *
     * @param id the id of the vehicle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
