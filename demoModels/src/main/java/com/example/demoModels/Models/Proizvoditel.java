package com.example.demoModels.Models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Proizvoditel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=120, message ="Наименование не может быть меньше 2 букв")
    private String name;
    @Size(min=10, max=10, message ="ИНН должен быть 10 цифр")
    private String  INN;
    /*@OneToOne(mappedBy = "proizvoditel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Product product;*/

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

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    /*public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }*/
}
