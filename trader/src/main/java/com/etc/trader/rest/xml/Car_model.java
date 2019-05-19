package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car_model {
    @XmlElement
    private String make;
    @XmlElement
    private String model;
    @XmlElement
    private String model_specification;

}
