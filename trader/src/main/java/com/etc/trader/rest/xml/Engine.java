package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Engine {
    @XmlElement
    private Long effect;
    @XmlElement
    private Long volume;
    @XmlElement
    private String fuel;

}
