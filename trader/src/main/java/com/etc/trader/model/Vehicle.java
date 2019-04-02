package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
@Getter
@Setter
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick")
    private String nick;

    @Column(name = "color")
    private String color;

    @Column(name = "make")
    private String make;

    @Column(name = "tires")
    private String tires;

    @Column(name = "max_fuel")
    private Long maxFuel;

    @Column(name = "chassis_id")
    private String chassisId;

    @Column(name = "max_km_tank")
    private Long maxKmTank;

    @Column(name = "fuel_system")
    private String fuelSystem;

    @Column(name = "gear_system")
    private String gearSystem;

    @Column(name = "drive")
    private String drive;

    @Column(name = "interior")
    private String interior;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "mileage")
    private Long mileage;

    @OneToMany(mappedBy = "vehicle")
    private Set<VehicleAlbum> vehicleAlbums = new HashSet<>();
    @OneToOne(mappedBy = "vehicle")
    @JsonIgnore
    private Listing listing;

    @ManyToOne
    @JsonIgnoreProperties("vehicles")
    private Company company;

    @ManyToOne
    @JsonIgnoreProperties("vehicles")
    private Model model;

}
