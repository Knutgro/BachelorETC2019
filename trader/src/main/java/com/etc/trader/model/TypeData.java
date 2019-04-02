package com.etc.trader.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "type_data")
@Getter
@Setter
public class TypeData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body_style")
    private String bodyStyle;

    @Column(name = "trim_level")
    private String trimLevel;

    @Column(name = "list_price")
    private Long listPrice;

    @Column(name = "effect")
    private Double effect;

    @Column(name = "range")
    private Double range;

    @Column(name = "battery_capacity")
    private Double batteryCapacity;

    @Column(name = "co_2")
    private Double co2;

    @Column(name = "nox")
    private Double nox;

    @Column(name = "engine_type")
    private String engineType;

    @ManyToOne
    @JsonIgnoreProperties("typeData")
    private Model model;

}
