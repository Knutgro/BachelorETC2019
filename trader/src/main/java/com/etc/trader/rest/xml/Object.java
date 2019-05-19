package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Object {
    @XmlElement
    private Object_head object_head;
    @XmlElement
    private Newcar newcar;

}
