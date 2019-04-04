package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
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
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    //@OneToMany
    private Set<User> users = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    //@OneToMany
    private Set<Vehicle> vehicles = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    //@OneToMany
    private Set<Listing> listings = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "company_region",
               joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "region_id", referencedColumnName = "id"))
    private Set<Region> regions = new HashSet<>();

    public Company() {}
    public Company(String name, String orgNr, String phoneNumber, Set<User> users, Set<Vehicle> vehicles, Set<Listing> listings, Set<Region> regions) {
        this.name = name;
        this.orgNr = orgNr;
        this.phoneNumber = phoneNumber;
        this.users = users;
        this.vehicles = vehicles;
        this.listings = listings;
        this.regions = regions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgNr() {
        return orgNr;
    }

    public Company orgNr(String orgNr) {
        this.orgNr = orgNr;
        return this;
    }

    public void setOrgNr(String orgNr) {
        this.orgNr = orgNr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Company phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Company users(Set<User> users) {
        this.users = users;
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

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Company vehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
        return this;
    }

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

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Listing> getListings() {
        return listings;
    }

    public Company listings(Set<Listing> listings) {
        this.listings = listings;
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

    public void setListings(Set<Listing> listings) {
        this.listings = listings;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public Company regions(Set<Region> regions) {
        this.regions = regions;
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

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        if (company.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", orgNr='" + getOrgNr() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                "}";
    }
}
