package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Listing.
 */
@Entity
@Table(name = "listing")
@Getter
@Setter
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
    private Double registration;

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
    @JsonIgnoreProperties("listings")
    private Company company;

}
