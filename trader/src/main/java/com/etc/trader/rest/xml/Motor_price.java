package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Motor_price {
    @XmlAttribute
    private String registrationtax_included;
    @XmlAttribute
    private String roadtax_included;
    @XmlElement
    private Long total;
    @XmlElement
    private Long registration;
}
