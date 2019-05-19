package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Object_location {
    @XmlElement
    private String streetaddress;
    @XmlElement
    private String zipcode;
    @XmlElement
    private String countrycode;
    @XmlElement
    private String address3;

}
