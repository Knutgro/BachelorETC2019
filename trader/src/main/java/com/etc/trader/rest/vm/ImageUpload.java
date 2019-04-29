package com.etc.trader.rest.vm;

import com.etc.trader.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class ImageUpload {
    @Lob
    private byte[] image;

    private String imageContentType;

    private Long vehicle_id;

    public ImageUpload(byte[] image, String imageContentType, Long vehicle_id) {
        this.image = image;
        this.imageContentType = imageContentType;
        this.vehicle_id = vehicle_id;
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

    public Long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
}
