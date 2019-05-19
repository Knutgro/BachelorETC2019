package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Newcar {
    @XmlElement
    private Car_model car_model;
    @XmlElement
    private String year_model;
    @XmlElement
    private String body_type;
    @XmlElement
    private String registration_class;
    @XmlElement
    private Double milage;
    @XmlElement
    private Motor_price motor_price;
    @XmlElement
    private String exterior_colour_main;
    @XmlElement
    private String exterior_colour;
    @XmlElement
    private List<String> equipment;
    @XmlElement
    private Long no_of_doors;
    @XmlElement
    private Engine engine;
    @XmlElement
    private Transmission transmission;
    @XmlElement
    private Wheel_drive wheel_drive;
    @XmlElement
    private String description;
    @XmlElement
    private Long no_of_owners;
    @XmlElement
    private Contact contact;

}
