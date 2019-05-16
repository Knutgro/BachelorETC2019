package com.etc.trader.rest;


import com.etc.trader.model.VehicleAlbum;
import com.etc.trader.repository.VehicleAlbumRepository;
import com.etc.trader.repository.VehicleRepository;
import com.etc.trader.rest.errors.BadRequestAlertException;
import com.etc.trader.rest.util.HeaderUtil;
import com.etc.trader.rest.vm.ImageUpload;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VehicleAlbum.
 */
@RestController
@RequestMapping("/api")
public class VehicleAlbumResource {

    private static final String ENTITY_NAME = "vehicleAlbum";

    private final VehicleAlbumRepository vehicleAlbumRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleAlbumResource(VehicleAlbumRepository vehicleAlbumRepository,
                                VehicleRepository vehicleRepository) {
        this.vehicleAlbumRepository = vehicleAlbumRepository;
        this.vehicleRepository = vehicleRepository;

    }

    /**
     * POST  /vehicle-albums : Create a new vehicleAlbum.
     *
     * @param vehicleAlbum the vehicleAlbum to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicleAlbum, or with status 400 (Bad Request) if the vehicleAlbum has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicle-albums")
    public ResponseEntity<VehicleAlbum> createVehicleAlbum(@RequestBody ImageUpload imageUpload) throws URISyntaxException {
        if (imageUpload.getVehicle_id() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleAlbum album = new VehicleAlbum();
        album.setVehicle(vehicleRepository.findVehicleById(imageUpload.getVehicle_id()));
        album.setImage(imageUpload.getImage());
        album.setImageContentType(imageUpload.getImageContentType());
        VehicleAlbum result = vehicleAlbumRepository.save(album);
        return ResponseEntity.created(new URI("/api/vehicle-albums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vehicle-albums : Updates an existing vehicleAlbum.
     *
     * @param vehicleAlbum the vehicleAlbum to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicleAlbum,
     * or with status 400 (Bad Request) if the vehicleAlbum is not valid,
     * or with status 500 (Internal Server Error) if the vehicleAlbum couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicle-albums")
    public ResponseEntity<VehicleAlbum> updateVehicleAlbum(@RequestBody ImageUpload imageUpload) throws URISyntaxException {
        if (imageUpload.getVehicle_id() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleAlbum album = new VehicleAlbum();
        album.setVehicle(vehicleRepository.findVehicleById(imageUpload.getVehicle_id()));
        //album.setImage(imageUpload.getImage());
        album.setImageContentType(imageUpload.getImageContentType());
        VehicleAlbum result = vehicleAlbumRepository.save(album);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, album.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicle-albums : get all the vehicleAlbums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vehicleAlbums in body
     */
    @GetMapping("/vehicle-albums")
    public List<VehicleAlbum> getAllVehicleAlbums() {
        return vehicleAlbumRepository.findAll();
    }

    /**
     * GET  /vehicle-albums/:id : get the "id" vehicleAlbum.
     *
     * @param id the id of the vehicleAlbum to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicleAlbum, or with status 404 (Not Found)
     */
    @GetMapping("/vehicle-albums/{id}")
    public ResponseEntity<VehicleAlbum> getVehicleAlbum(@PathVariable Long id) {
        Optional<VehicleAlbum> vehicleAlbum = vehicleAlbumRepository.findOneByVehicle_id(id);
        return ResponseUtil.wrapOrNotFound(vehicleAlbum);
    }

    /**
     * DELETE  /vehicle-albums/:id : delete the "id" vehicleAlbum.
     *
     * @param id the id of the vehicleAlbum to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicle-albums/{id}")
    public ResponseEntity<Void> deleteVehicleAlbum(@PathVariable Long id) {
        vehicleAlbumRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
