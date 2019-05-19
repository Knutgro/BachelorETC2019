package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {
    @XmlElement
    private String name;
    @XmlElement
    private String phone;
    @XmlElement
    private String email;
    @XmlElement
    private Long fax;
    @XmlElement
    private String url;
}
