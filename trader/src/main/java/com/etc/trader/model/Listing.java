package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Listing.
 */
@Entity
@Table(name = "listing")
public class Listing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "url_text")
    private String urlText;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "registration_tax_included")
    private Boolean registrationTaxIncluded;

    @Column(name = "registration_exemption")
    private Boolean registrationExemption;

    @Column(name = "vat_included")
    private Boolean vatIncluded;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "net_price")
    private Double netPrice;

    @Column(name = "registration")
    private String registration;

    @Column(name = "currency")
    private String currency;

    @Column(name = "position")
    private String position;

    @Column(name = "no_of_owners")
    private Long noOfOwners;

    @Column(name = "car_condition")
    private String carCondition;

    @Column(name = "date_created")
    private Instant dateCreated;

    @Column(name = "date_expired")
    private Instant dateExpired;

    @OneToOne
    @JoinColumn(unique = true)
    private Vehicle vehicle;

    @ManyToOne
    //@JsonIgnore
    @JsonIgnoreProperties("listings")
    private Company company;

    public Listing() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Listing title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Listing description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlText() {
        return urlText;
    }

    public Listing urlText(String urlText) {
        this.urlText = urlText;
        return this;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }

    public String getKeywords() {
        return keywords;
    }

    public Listing keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Boolean isRegistrationTaxIncluded() {
        return registrationTaxIncluded;
    }

    public Listing registrationTaxIncluded(Boolean registrationTaxIncluded) {
        this.registrationTaxIncluded = registrationTaxIncluded;
        return this;
    }

    public void setRegistrationTaxIncluded(Boolean registrationTaxIncluded) {
        this.registrationTaxIncluded = registrationTaxIncluded;
    }

    public Boolean isRegistrationExemption() {
        return registrationExemption;
    }

    public Listing registrationExemption(Boolean registrationExemption) {
        this.registrationExemption = registrationExemption;
        return this;
    }

    public void setRegistrationExemption(Boolean registrationExemption) {
        this.registrationExemption = registrationExemption;
    }

    public Boolean isVatIncluded() {
        return vatIncluded;
    }

    public Listing vatIncluded(Boolean vatIncluded) {
        this.vatIncluded = vatIncluded;
        return this;
    }

    public void setVatIncluded(Boolean vatIncluded) {
        this.vatIncluded = vatIncluded;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Listing totalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public Listing netPrice(Double netPrice) {
        this.netPrice = netPrice;
        return this;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    public String getRegistration() {
        return registration;
    }

    public Listing registration(String registration) {
        this.registration = registration;
        return this;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCurrency() {
        return currency;
    }

    public Listing currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPosition() {
        return position;
    }

    public Listing position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getNoOfOwners() {
        return noOfOwners;
    }

    public Listing noOfOwners(Long noOfOwners) {
        this.noOfOwners = noOfOwners;
        return this;
    }

    public void setNoOfOwners(Long noOfOwners) {
        this.noOfOwners = noOfOwners;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public Listing carCondition(String carCondition) {
        this.carCondition = carCondition;
        return this;
    }

    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public Listing dateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateExpired() {
        return dateExpired;
    }

    public Listing dateExpired(Instant dateExpired) {
        this.dateExpired = dateExpired;
        return this;
    }

    public void setDateExpired(Instant dateExpired) {
        this.dateExpired = dateExpired;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Listing vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Company getCompany() {
        return company;
    }

    public Listing company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        Listing listing = (Listing) o;
        if (listing.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), listing.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", description='" + getDescription() + "'" +
                ", urlText='" + getUrlText() + "'" +
                ", keywords='" + getKeywords() + "'" +
                ", registrationTaxIncluded='" + isRegistrationTaxIncluded() + "'" +
                ", registrationExemption='" + isRegistrationExemption() + "'" +
                ", vatIncluded='" + isVatIncluded() + "'" +
                ", totalPrice=" + getTotalPrice() +
                ", netPrice=" + getNetPrice() +
                ", registration=" + getRegistration() +
                ", currency='" + getCurrency() + "'" +
                ", position='" + getPosition() + "'" +
                ", noOfOwners=" + getNoOfOwners() +
                ", carCondition='" + getCarCondition() + "'" +
                ", dateCreated='" + getDateCreated() + "'" +
                ", dateExpired='" + getDateExpired() + "'" +
                "}";
    }

}
