package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * A VehicleImage.
 */
@Entity
@Table(name = "vehicle_image")
public class VehicleImage implements Serializable {

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
    @JsonIgnoreProperties("vehicleImage")
    private Vehicle vehicle;

    public VehicleImage(byte[] image, String imageContentType, Vehicle vehicle) {
        this.image = image;
        this.imageContentType = imageContentType;
        this.vehicle = vehicle;
    }

    public VehicleImage() {}

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
        VehicleImage vehicleImage = (VehicleImage) o;
        if (vehicleImage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleImage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleImage{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", imageContentType='" + imageContentType + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
