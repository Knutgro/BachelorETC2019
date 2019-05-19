package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Object_head {
    @XmlElement
    private Long orderno;
    @XmlElement
    private String user_reference;
    @XmlElement
    private Overwrite_mmo overwrite_mmo;
    @XmlElement
    private String from_fromdate;
    @XmlElement
    private String todate;
    @XmlElement
    private Object_location object_location;


}
