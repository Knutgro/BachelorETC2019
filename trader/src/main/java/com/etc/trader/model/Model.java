package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Model.
 */
@Entity
@Table(name = "model")
@Getter
@Setter
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "m_year")
    private Integer year;

    //@OneToMany(mappedBy = "model")
    @OneToMany
    private Set<Vehicle> vehicles = new HashSet<>();

    //@OneToMany(mappedBy = "model")
    @OneToMany
    private Set<TypeData> typeData = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("models")
    private Brand brand;

}
