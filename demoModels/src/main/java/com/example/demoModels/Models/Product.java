package com.example.demoModels.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameproduct;
    private Double price;
    private String country;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull
    private Date dates;

    /*@NotNull
    @OneToOne(optional = true, cascade =CascadeType.ALL)
    @JoinColumn(name="type_id")
    private Type type;

    @NotNull
    @OneToOne(optional = true, cascade =CascadeType.ALL)
    @JoinColumn(name="proizvoditel_id")
    private Proizvoditel proizvoditel;*/

    @ManyToMany
    @JoinTable(name="TovarPostavkiTovara",
            joinColumns=@JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="postavkatovara_id"))
    private Set<PostavkaTovara> Pods = new HashSet<PostavkaTovara>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    /*public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Proizvoditel getProizvoditel() {
        return proizvoditel;
    }

    public void setProizvoditel(Proizvoditel proizvoditel) {
        this.proizvoditel = proizvoditel;
    }*/

    public Set<PostavkaTovara> getPods() {
        return Pods;
    }

    public void setPods(Set<PostavkaTovara> pods) {
        Pods = pods;
    }
}
