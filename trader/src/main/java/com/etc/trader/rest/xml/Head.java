package com.etc.trader.rest.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Head {
    @XmlElement
    private String partner;

    @XmlElement
    private  String provider;

    @XmlElement
    private  String upload_reference;


    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUpload_reference() {
        return upload_reference;
    }

    public void setUpload_reference(String upload_reference) {
        this.upload_reference = upload_reference;
    }
}
