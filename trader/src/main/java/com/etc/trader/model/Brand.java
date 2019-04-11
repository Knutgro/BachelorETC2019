package com.etc.trader.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Brand.
 */
@Entity
@Table(name = "brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;


    @OneToMany(mappedBy = "brand")
    //@OneToMany
    private Set<Model> models = new HashSet<>();

    public Brand() {
    }

    public Brand(String name, String origin, byte[] logo, String logoContentType, Set<Model> models) {
        this.name = name;
        this.origin = origin;
        this.logo = logo;
        this.logoContentType = logoContentType;
        this.models = models;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id) &&
                Objects.equals(name, brand.name) &&
                Objects.equals(origin, brand.origin) &&
                Arrays.equals(logo, brand.logo) &&
                Objects.equals(logoContentType, brand.logoContentType) &&
                Objects.equals(models, brand.models);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, origin, logoContentType, models);
        result = 31 * result + Arrays.hashCode(logo);
        return result;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", logoContentType='" + logoContentType + '\'' +
                ", models=" + models +
                '}';
    }
}
