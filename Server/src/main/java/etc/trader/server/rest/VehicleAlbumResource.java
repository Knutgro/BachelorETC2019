package etc.trader.server.rest;

import etc.trader.server.model.VehicleAlbum;
import etc.trader.server.repository.VehicleAlbumRepository;
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
 * REST controller for managing VehicleAlbum.
 */
@RestController
@RequestMapping("/api")
public class VehicleAlbumResource {

    private final Logger log = LoggerFactory.getLogger(VehicleAlbumResource.class);

    private static final String ENTITY_NAME = "vehicleAlbum";

    private final VehicleAlbumRepository vehicleAlbumRepository;

    public VehicleAlbumResource(VehicleAlbumRepository vehicleAlbumRepository) {
        this.vehicleAlbumRepository = vehicleAlbumRepository;
    }

    /**
     * POST  /vehicle-albums : Create a new vehicleAlbum.
     *
     * @param vehicleAlbum the vehicleAlbum to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicleAlbum, or with status 400 (Bad Request) if the vehicleAlbum has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicle-albums")
    public ResponseEntity<VehicleAlbum> createVehicleAlbum(@RequestBody VehicleAlbum vehicleAlbum) throws URISyntaxException {
        log.debug("REST request to save VehicleAlbum : {}", vehicleAlbum);
        if (vehicleAlbum.getId() != null) {
            throw new BadRequestAlertException("A new vehicleAlbum cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleAlbum result = vehicleAlbumRepository.save(vehicleAlbum);
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
    public ResponseEntity<VehicleAlbum> updateVehicleAlbum(@RequestBody VehicleAlbum vehicleAlbum) throws URISyntaxException {
        log.debug("REST request to update VehicleAlbum : {}", vehicleAlbum);
        if (vehicleAlbum.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleAlbum result = vehicleAlbumRepository.save(vehicleAlbum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehicleAlbum.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicle-albums : get all the vehicleAlbums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vehicleAlbums in body
     */
    @GetMapping("/vehicle-albums")
    public List<VehicleAlbum> getAllVehicleAlbums() {
        log.debug("REST request to get all VehicleAlbums");
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
        log.debug("REST request to get VehicleAlbum : {}", id);
        Optional<VehicleAlbum> vehicleAlbum = vehicleAlbumRepository.findById(id);
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
        log.debug("REST request to delete VehicleAlbum : {}", id);
        vehicleAlbumRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
