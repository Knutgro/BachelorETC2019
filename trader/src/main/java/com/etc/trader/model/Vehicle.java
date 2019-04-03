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

    @Column(name = "first_registration")
    private String firstRegistration;

    @Column(name = "registration_class")
    private String registrationClass;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "warranty")
    private String warranty;

    @Column(name = "warranty_duration")
    private String warrantyDuration;

    @Column(name = "warranty_distance")
    private Long warrantyDistance;

    @Column(name = "warranty_url")
    private String warrantyUrl;

    @Column(name = "car_premium")
    private String carPremium;

    @Column(name = "car_premium_link")
    private String carPremiumLink;

    @Column(name = "exterior_color_main")
    private String exteriorColorMain;

    @Column(name = "exterior_color")
    private String exteriorColor;

    @Column(name = "interior_color")
    private String interiorColor;

    @Column(name = "interior_type")
    private String interiorType;

    @Column(name = "tires")
    private String tires;

    @Column(name = "max_fuel")
    private Long maxFuel;

    @Column(name = "chassis_id")
    private String chassisId;

    @Column(name = "max_km_tank")
    private Long maxKmTank;

    @Column(name = "mileage")
    private Long mileage;

    @Column(name = "service_history")
    private Boolean serviceHistory;

    @Column(name = "service_plan_followed")
    private Boolean servicePlanFollowed;

    @Column(name = "finance_partner")
    private String financePartner;

    @Column(name = "insurance_partner")
    private String insurancePartner;

    //@OneToMany(mappedBy = "vehicle")
    @OneToMany
    private Set<VehicleAlbum> vehicleAlbums = new HashSet<>();

    //@OneToOne(mappedBy = "vehicle")
    @OneToOne
    @JsonIgnore
    private Listing listing;

    @ManyToOne
    @JsonIgnoreProperties("vehicles")
    private Company company;

    @ManyToOne
    @JsonIgnoreProperties("vehicles")
    private Model model;

}
