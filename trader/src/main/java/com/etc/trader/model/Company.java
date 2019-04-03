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
 * A Company.
 */
@Entity
@Table(name = "company")
@Getter
@Setter
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "org_nr")
    private String orgNr;

    @Column(name = "phone_number")
    private String phoneNumber;

    //@OneToMany(mappedBy = "company")
    @OneToMany
    private Set<User> users = new HashSet<>();

    //@OneToMany(mappedBy = "company")
    @OneToMany
    private Set<Vehicle> vehicles = new HashSet<>();

    //@OneToMany(mappedBy = "company")
    @OneToMany
    private Set<Listing> listings = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "company_region",
               joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "region_id", referencedColumnName = "id"))
    private Set<Region> regions = new HashSet<>();


    public Company addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setCompany(this);
        return this;
    }

    public Company removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
        vehicle.setCompany(null);
        return this;
    }

    public Company addUser(User user) {
        this.users.add(user);
        user.setCompany(this);
        return this;
    }

    public Company removeUser(User user) {
        this.users.remove(user);
        user.setCompany(null);
        return this;
    }

    public Company addListing(Listing listing) {
        this.listings.add(listing);
        listing.setCompany(this);
        return this;
    }

    public Company removeListing(Listing listing) {
        this.listings.remove(listing);
        listing.setCompany(null);
        return this;
    }

    public Company addRegion(Region region) {
        this.regions.add(region);
        region.getCompanies().add(this);
        return this;
    }

    public Company removeRegion(Region region) {
        this.regions.remove(region);
        region.getCompanies().remove(this);
        return this;
    }
}
