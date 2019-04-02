package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A VehicleAlbum.
 */
@Entity
@Table(name = "vehicle_album")
@Getter
@Setter
public class VehicleAlbum implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ManyToOne
    @JsonIgnoreProperties("vehicleAlbums")
    private Vehicle vehicle;
    
}
