package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A VehicleAlbum.
 */
@Entity
@Table(name = "vehicle_album")
public class VehicleAlbum implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picture_url")
    private String pictureUrl;

    @JsonIgnore
    @ManyToOne
    @JsonIgnoreProperties("vehicleAlbums")
    private Vehicle vehicle;

    VehicleAlbum() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public VehicleAlbum pictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleAlbum vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
        VehicleAlbum vehicleAlbum = (VehicleAlbum) o;
        if (vehicleAlbum.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleAlbum.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleAlbum{" +
                "id=" + getId() +
                ", pictureUrl='" + getPictureUrl() + "'" +
                "}";
    }
    
}
