package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IAD.IF.CAR")
public class ListingXML {
    @XmlElement
    private Head head;

    @XmlElement
    private Object object;
}
