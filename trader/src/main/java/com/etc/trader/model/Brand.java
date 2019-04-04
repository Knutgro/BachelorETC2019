package com.etc.trader.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany(mappedBy = "brand")
    //@OneToMany
    private Set<Model> models = new HashSet<>();

    public Brand() {
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

    public Brand name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public Brand origin(String origin) {
        this.origin = origin;
        return this;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Set<Model> getModels() {
        return models;
    }

    public Brand models(Set<Model> models) {
        this.models = models;
        return this;
    }

    public Brand addModel(Model model) {
        this.models.add(model);
        model.setBrand(this);
        return this;
    }

    public Brand removeModel(Model model) {
        this.models.remove(model);
        model.setBrand(null);
        return this;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        if (brand.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), brand.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", origin='" + getOrigin() + "'" +
                "}";
    }
}
