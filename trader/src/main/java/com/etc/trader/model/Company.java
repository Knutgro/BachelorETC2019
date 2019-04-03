package com.etc.trader.model;


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

    @OneToMany(mappedBy = "company")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "company")
    private Set<Vehicle> vehicles = new HashSet<>();

    @OneToMany(mappedBy = "company")
    private Set<Listing> listings = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "company_region",
               joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "region_id", referencedColumnName = "id"))
    private Set<Region> regions = new HashSet<>();

}
