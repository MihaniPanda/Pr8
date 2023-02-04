package com.example.demoModels.Models;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=5, max=120, message ="Наименование не может быть меньше 5 букв")
    private String namee;
    /*@OneToOne(mappedBy = "type", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Product product;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

/*    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }*/
}
