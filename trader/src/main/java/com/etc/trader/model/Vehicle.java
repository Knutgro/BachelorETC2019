package com.etc.trader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
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

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OneToMany
    private Set<VehicleImage> vehicleImages = new HashSet<>();

    @OneToOne(mappedBy = "vehicle")
    //@OneToOne
    @JsonIgnore
    private Listing listing;

    @ManyToOne
    @JsonIgnore
    @JsonIgnoreProperties("vehicles")
    private Company company;

    @ManyToOne
    @JsonIgnoreProperties("vehicles")
    private TypeData typeData;

    public Vehicle() {
    }

    public Vehicle(String nick, String firstRegistration, String registrationClass, String regNo, String warranty, String warrantyDuration, Long warrantyDistance, String warrantyUrl, String carPremium, String carPremiumLink, String exteriorColorMain, String exteriorColor, String interiorColor, String interiorType, String tires, Long maxFuel, String chassisId, Long maxKmTank, Long mileage, Boolean serviceHistory, Boolean servicePlanFollowed, String financePartner, String insurancePartner, Set<VehicleImage> vehicleImages, Listing listing, Company company, TypeData typeData) {
        this.nick = nick;
        this.firstRegistration = firstRegistration;
        this.registrationClass = registrationClass;
        this.regNo = regNo;
        this.warranty = warranty;
        this.warrantyDuration = warrantyDuration;
        this.warrantyDistance = warrantyDistance;
        this.warrantyUrl = warrantyUrl;
        this.carPremium = carPremium;
        this.carPremiumLink = carPremiumLink;
        this.exteriorColorMain = exteriorColorMain;
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.interiorType = interiorType;
        this.tires = tires;
        this.maxFuel = maxFuel;
        this.chassisId = chassisId;
        this.maxKmTank = maxKmTank;
        this.mileage = mileage;
        this.serviceHistory = serviceHistory;
        this.servicePlanFollowed = servicePlanFollowed;
        this.financePartner = financePartner;
        this.insurancePartner = insurancePartner;
        this.vehicleImages = vehicleImages;
        this.listing = listing;
        this.company = company;
        this.typeData = typeData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public Vehicle nick(String nick) {
        this.nick = nick;
        return this;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFirstRegistration() {
        return firstRegistration;
    }

    public Vehicle firstRegistration(String firstRegistration) {
        this.firstRegistration = firstRegistration;
        return this;
    }

    public void setFirstRegistration(String firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public String getRegistrationClass() {
        return registrationClass;
    }

    public Vehicle registrationClass(String registrationClass) {
        this.registrationClass = registrationClass;
        return this;
    }

    public void setRegistrationClass(String registrationClass) {
        this.registrationClass = registrationClass;
    }

    public String getRegNo() {
        return regNo;
    }

    public Vehicle regNo(String regNo) {
        this.regNo = regNo;
        return this;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getWarranty() {
        return warranty;
    }

    public Vehicle warranty(String warranty) {
        this.warranty = warranty;
        return this;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getWarrantyDuration() {
        return warrantyDuration;
    }

    public Vehicle warrantyDuration(String warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
        return this;
    }

    public void setWarrantyDuration(String warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
    }

    public Long getWarrantyDistance() {
        return warrantyDistance;
    }

    public Vehicle warrantyDistance(Long warrantyDistance) {
        this.warrantyDistance = warrantyDistance;
        return this;
    }

    public void setWarrantyDistance(Long warrantyDistance) {
        this.warrantyDistance = warrantyDistance;
    }

    public String getWarrantyUrl() {
        return warrantyUrl;
    }

    public Vehicle warrantyUrl(String warrantyUrl) {
        this.warrantyUrl = warrantyUrl;
        return this;
    }

    public void setWarrantyUrl(String warrantyUrl) {
        this.warrantyUrl = warrantyUrl;
    }

    public String getCarPremium() {
        return carPremium;
    }

    public Vehicle carPremium(String carPremium) {
        this.carPremium = carPremium;
        return this;
    }

    public void setCarPremium(String carPremium) {
        this.carPremium = carPremium;
    }

    public String getCarPremiumLink() {
        return carPremiumLink;
    }

    public Vehicle carPremiumLink(String carPremiumLink) {
        this.carPremiumLink = carPremiumLink;
        return this;
    }

    public void setCarPremiumLink(String carPremiumLink) {
        this.carPremiumLink = carPremiumLink;
    }

    public String getExteriorColorMain() {
        return exteriorColorMain;
    }

    public Vehicle exteriorColorMain(String exteriorColorMain) {
        this.exteriorColorMain = exteriorColorMain;
        return this;
    }

    public void setExteriorColorMain(String exteriorColorMain) {
        this.exteriorColorMain = exteriorColorMain;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public Vehicle exteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
        return this;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public Vehicle interiorColor(String interiorColor) {
        this.interiorColor = interiorColor;
        return this;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getInteriorType() {
        return interiorType;
    }

    public Vehicle interiorType(String interiorType) {
        this.interiorType = interiorType;
        return this;
    }

    public void setInteriorType(String interiorType) {
        this.interiorType = interiorType;
    }

    public String getTires() {
        return tires;
    }

    public Vehicle tires(String tires) {
        this.tires = tires;
        return this;
    }

    public void setTires(String tires) {
        this.tires = tires;
    }

    public Long getMaxFuel() {
        return maxFuel;
    }

    public Vehicle maxFuel(Long maxFuel) {
        this.maxFuel = maxFuel;
        return this;
    }

    public void setMaxFuel(Long maxFuel) {
        this.maxFuel = maxFuel;
    }

    public String getChassisId() {
        return chassisId;
    }

    public Vehicle chassisId(String chassisId) {
        this.chassisId = chassisId;
        return this;
    }

    public void setChassisId(String chassisId) {
        this.chassisId = chassisId;
    }

    public Long getMaxKmTank() {
        return maxKmTank;
    }

    public Vehicle maxKmTank(Long maxKmTank) {
        this.maxKmTank = maxKmTank;
        return this;
    }

    public void setMaxKmTank(Long maxKmTank) {
        this.maxKmTank = maxKmTank;
    }

    public Long getMileage() {
        return mileage;
    }

    public Vehicle mileage(Long mileage) {
        this.mileage = mileage;
        return this;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Boolean isServiceHistory() {
        return serviceHistory;
    }

    public Vehicle serviceHistory(Boolean serviceHistory) {
        this.serviceHistory = serviceHistory;
        return this;
    }

    public void setServiceHistory(Boolean serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public Boolean isServicePlanFollowed() {
        return servicePlanFollowed;
    }

    public Vehicle servicePlanFollowed(Boolean servicePlanFollowed) {
        this.servicePlanFollowed = servicePlanFollowed;
        return this;
    }

    public void setServicePlanFollowed(Boolean servicePlanFollowed) {
        this.servicePlanFollowed = servicePlanFollowed;
    }

    public String getFinancePartner() {
        return financePartner;
    }

    public Vehicle financePartner(String financePartner) {
        this.financePartner = financePartner;
        return this;
    }

    public void setFinancePartner(String financePartner) {
        this.financePartner = financePartner;
    }

    public String getInsurancePartner() {
        return insurancePartner;
    }

    public Vehicle insurancePartner(String insurancePartner) {
        this.insurancePartner = insurancePartner;
        return this;
    }

    public void setInsurancePartner(String insurancePartner) {
        this.insurancePartner = insurancePartner;
    }

    public Set<VehicleImage> getVehicleImages() {
        return vehicleImages;
    }

    public Vehicle vehicleAlbums(Set<VehicleImage> vehicleImages) {
        this.vehicleImages = vehicleImages;
        return this;
    }

    public Vehicle addVehicleAlbum(VehicleImage vehicleImage) {
        this.vehicleImages.add(vehicleImage);
        vehicleImage.setVehicle(this);
        return this;
    }

    public Vehicle removeVehicleAlbum(VehicleImage vehicleImage) {
        this.vehicleImages.remove(vehicleImage);
        vehicleImage.setVehicle(null);
        return this;
    }

    public void setVehicleImages(Set<VehicleImage> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }

    public Listing getListing() {
        return listing;
    }

    public Vehicle listing(Listing listing) {
        this.listing = listing;
        return this;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Company getCompany() {
        return company;
    }

    public Vehicle company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public TypeData getTypeData() {
        return typeData;
    }

    public Vehicle typeData(TypeData typeData) {
        this.typeData = typeData;
        return this;
    }

    public void setTypeData(TypeData typeData) {
        this.typeData = typeData;
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
        Vehicle vehicle = (Vehicle) o;
        if (vehicle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + getId() +
                ", nick='" + getNick() + "'" +
                ", firstRegistration='" + getFirstRegistration() + "'" +
                ", registrationClass='" + getRegistrationClass() + "'" +
                ", regNo='" + getRegNo() + "'" +
                ", warranty='" + getWarranty() + "'" +
                ", warrantyDuration='" + getWarrantyDuration() + "'" +
                ", warrantyDistance=" + getWarrantyDistance() +
                ", warrantyUrl='" + getWarrantyUrl() + "'" +
                ", carPremium='" + getCarPremium() + "'" +
                ", carPremiumLink='" + getCarPremiumLink() + "'" +
                ", exteriorColorMain='" + getExteriorColorMain() + "'" +
                ", exteriorColor='" + getExteriorColor() + "'" +
                ", interiorColor='" + getInteriorColor() + "'" +
                ", interiorType='" + getInteriorType() + "'" +
                ", tires='" + getTires() + "'" +
                ", maxFuel=" + getMaxFuel() +
                ", chassisId='" + getChassisId() + "'" +
                ", maxKmTank=" + getMaxKmTank() +
                ", mileage=" + getMileage() +
                ", serviceHistory='" + isServiceHistory() + "'" +
                ", servicePlanFollowed='" + isServicePlanFollowed() + "'" +
                ", financePartner='" + getFinancePartner() + "'" +
                ", insurancePartner='" + getInsurancePartner() + "'" +
                "}";
    }

}
