package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "type_data")
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
    private Double engineVolume;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "range")
    private Double range;

    @Column(name = "battery_capacity")
    private Double batteryCapacity;

    @Column(name = "co_2")
    private Double co2;

    @Column(name = "nox")
    private Double nox;

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

    @JsonIgnore
    @OneToMany(mappedBy = "typeData")
    private Set<Vehicle> vehicles = new HashSet<>();

    @ManyToOne
    //@JsonIgnore
    @JsonIgnoreProperties("typeData")
    private Model model;

    public TypeData() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public TypeData typeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBodyType() {
        return bodyType;
    }

    public TypeData bodyType(String bodyType) {
        this.bodyType = bodyType;
        return this;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Long getEngineEffect() {
        return engineEffect;
    }

    public TypeData engineEffect(Long engineEffect) {
        this.engineEffect = engineEffect;
        return this;
    }

    public void setEngineEffect(Long engineEffect) {
        this.engineEffect = engineEffect;
    }

    public Double getEngineVolume() {
        return engineVolume;
    }

    public TypeData engineVolume(Double engineVolume) {
        this.engineVolume = engineVolume;
        return this;
    }

    public void setEngineVolume(Double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getFuel() {
        return fuel;
    }

    public TypeData fuel(String fuel) {
        this.fuel = fuel;
        return this;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Double getRange() {
        return range;
    }

    public TypeData range(Double range) {
        this.range = range;
        return this;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Double getBatteryCapacity() {
        return batteryCapacity;
    }

    public TypeData batteryCapacity(Double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }

    public void setBatteryCapacity(Double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Double getCo2() {
        return co2;
    }

    public TypeData co2(Double co2) {
        this.co2 = co2;
        return this;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getNox() {
        return nox;
    }

    public TypeData nox(Double nox) {
        this.nox = nox;
        return this;
    }

    public void setNox(Double nox) {
        this.nox = nox;
    }

    public String getTransmission() {
        return transmission;
    }

    public TypeData transmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getWheelDrive() {
        return wheelDrive;
    }

    public TypeData wheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
        return this;
    }

    public void setWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    public Long getSizeOfBoot() {
        return sizeOfBoot;
    }

    public TypeData sizeOfBoot(Long sizeOfBoot) {
        this.sizeOfBoot = sizeOfBoot;
        return this;
    }

    public void setSizeOfBoot(Long sizeOfBoot) {
        this.sizeOfBoot = sizeOfBoot;
    }

    public Long getWeight() {
        return weight;
    }

    public TypeData weight(Long weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getNoOfDoors() {
        return noOfDoors;
    }

    public TypeData noOfDoors(Long noOfDoors) {
        this.noOfDoors = noOfDoors;
        return this;
    }

    public void setNoOfDoors(Long noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public Long getNoOfSeats() {
        return noOfSeats;
    }

    public TypeData noOfSeats(Long noOfSeats) {
        this.noOfSeats = noOfSeats;
        return this;
    }

    public void setNoOfSeats(Long noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public TypeData vehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
        return this;
    }

    public TypeData addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setTypeData(this);
        return this;
    }

    public TypeData removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
        vehicle.setTypeData(null);
        return this;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Model getModel() {
        return model;
    }

    public TypeData model(Model model) {
        this.model = model;
        return this;
    }

    public void setModel(Model model) {
        this.model = model;
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
        TypeData typeData = (TypeData) o;
        if (typeData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeData{" +
                "id=" + getId() +
                ", typeName='" + getTypeName() + "'" +
                ", bodyType='" + getBodyType() + "'" +
                ", engineEffect=" + getEngineEffect() +
                ", engineVolume=" + getEngineVolume() +
                ", fuel='" + getFuel() + "'" +
                ", range=" + getRange() +
                ", batteryCapacity=" + getBatteryCapacity() +
                ", co2=" + getCo2() +
                ", nox=" + getNox() +
                ", transmission='" + getTransmission() + "'" +
                ", wheelDrive='" + getWheelDrive() + "'" +
                ", sizeOfBoot=" + getSizeOfBoot() +
                ", weight=" + getWeight() +
                ", noOfDoors=" + getNoOfDoors() +
                ", noOfSeats=" + getNoOfSeats() +
                "}";
    }

}
