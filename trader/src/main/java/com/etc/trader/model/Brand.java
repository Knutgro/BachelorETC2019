package com.etc.trader.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Brand.
 */
@Entity
@Table(name = "brand")
@Getter
@Setter
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    //@OneToMany(mappedBy = "brand")
    @OneToMany
    private Set<Model> models = new HashSet<>();

}
