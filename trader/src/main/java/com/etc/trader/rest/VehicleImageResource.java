package com.etc.trader.rest;

import com.etc.trader.model.VehicleImage;
import com.etc.trader.repository.VehicleImageRepository;
import com.etc.trader.repository.VehicleRepository;
import com.etc.trader.rest.errors.BadRequestAlertException;
import com.etc.trader.rest.util.HeaderUtil;
import com.etc.trader.rest.vm.ImageUpload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing VehicleImage.
 */
@RestController
@RequestMapping("/api")
public class VehicleImageResource {

    private static final String ENTITY_NAME = "vehicleImage";

    private final VehicleImageRepository vehicleImageRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleImageResource(VehicleImageRepository vehicleImageRepository,
                                VehicleRepository vehicleRepository) {
        this.vehicleImageRepository = vehicleImageRepository;
        this.vehicleRepository = vehicleRepository;

    }

    /**
     * POST  /vehicle-albums : Create a new vehicleAlbum.
     *
     * @param vehicleImage the vehicleAlbum to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicleAlbum, or with status 400 (Bad Request) if the vehicleAlbum has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicle-image")
    public ResponseEntity<VehicleImage> createVehicleImage(@RequestBody ImageUpload imageUpload) throws URISyntaxException {
        if (imageUpload.getVehicle_id() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleImage album = new VehicleImage();
        album.setVehicle(vehicleRepository.findVehicleById(imageUpload.getVehicle_id()));
        album.setImage(imageUpload.getImage());
        album.setImageContentType(imageUpload.getImageContentType());
        VehicleImage result = vehicleImageRepository.save(album);
        return ResponseEntity.created(new URI("/api/vehicle-albums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vehicle-albums : Updates an existing vehicleAlbum.
     *
     * @param ImageUpload the vehicleAlbum to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicleAlbum,
     * or with status 400 (Bad Request) if the vehicleAlbum is not valid,
     * or with status 500 (Internal Server Error) if the vehicleAlbum couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicle-image")
    public ResponseEntity<VehicleImage> updateVehicleImage(@RequestBody ImageUpload imageUpload) throws URISyntaxException {
        if (imageUpload.getVehicle_id() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleImage album = new VehicleImage();
        album.setVehicle(vehicleRepository.findVehicleById(imageUpload.getVehicle_id()));
        //album.setImage(imageUpload.getImage());
        album.setImageContentType(imageUpload.getImageContentType());
        VehicleImage result = vehicleImageRepository.save(album);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, album.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicle-albums : get all the vehicleAlbums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vehicleAlbums in body
     */
    @GetMapping("/vehicle-image")
    public List<VehicleImage> getAllVehicleImage() {
        return vehicleImageRepository.findAll();
    }

    /**
     * GET  /vehicle-albums/:id : get the "id" vehicleAlbum.
     *
     * @param id the id of the vehicleAlbum to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicleAlbum, or with status 404 (Not Found)
     */
    @GetMapping("/vehicle-image/{id}")
    public List<VehicleImage> getVehicleImage(@PathVariable Long id) {
        return vehicleImageRepository.findAllByVehicle_id(id);
    }

    /**
     * DELETE  /vehicle-albums/:id : delete the "id" vehicleAlbum.
     *
     * @param id the id of the vehicleAlbum to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicle-image/{id}")
    public ResponseEntity<Void> deleteVehicleImage(@PathVariable Long id) {
        vehicleImageRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
