package com.etc.trader.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Model.
 */
@Entity
@Table(name = "model")
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "m_year")
    private Integer year;

    @JsonIgnore
    @OneToMany(mappedBy = "model")
    private Set<TypeData> typeData = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("models")
    private Brand brand;

    public Model() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Model name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public Model year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<TypeData> getTypeData() {
        return typeData;
    }

    public Model typeData(Set<TypeData> typeData) {
        this.typeData = typeData;
        return this;
    }

    public Model addTypeData(TypeData typeData) {
        this.typeData.add(typeData);
        typeData.setModel(this);
        return this;
    }

    public Model removeTypeData(TypeData typeData) {
        this.typeData.remove(typeData);
        typeData.setModel(null);
        return this;
    }

    public void setTypeData(Set<TypeData> typeData) {
        this.typeData = typeData;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model brand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        if (model.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), model.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", year=" + getYear() +
                "}";
    }

}
