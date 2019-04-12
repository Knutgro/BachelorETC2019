package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
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

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;


    @JsonIgnore
    @ManyToOne
    @JsonIgnoreProperties("vehicleAlbums")
    private Vehicle vehicle;

    VehicleAlbum() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

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
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", imageContentType='" + imageContentType + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
