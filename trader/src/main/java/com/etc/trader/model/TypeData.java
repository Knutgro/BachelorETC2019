package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "type_data")
@Getter
@Setter
public class TypeData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "engine_effect")
    private Long engineEffect;

    @Column(name = "engine_volume")
    private Long engineVolume;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "m_range")
    private Long range;

    @Column(name = "battery_capacity")
    private Long batteryCapacity;

    @Column(name = "co2")
    private Long co2;

    @Column(name = "nox")
    private Long nox;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "wheel_drive")
    private String wheelDrive;

    @Column(name = "size_of_boot")
    private Long sizeOfBoot;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "no_of_doors")
    private Long noOfDoors;

    @Column(name = "no_of_seats")
    private Long noOfSeats;

    @ManyToOne
    @JsonIgnoreProperties("typeData")
    private Model model;

}
