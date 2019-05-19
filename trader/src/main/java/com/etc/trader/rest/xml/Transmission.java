package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transmission {
    @XmlAttribute
    private String searchkey;

}
