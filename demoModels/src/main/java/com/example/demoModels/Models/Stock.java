package com.example.demoModels.Models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=5, max=120, message ="Адресс не может быть меньше 5 букв")
    private String adress;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<PostavkaTovara> postavkatovara;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Collection<PostavkaTovara> getPostavkatovara() {
        return postavkatovara;
    }

    public void setPostavkatovara(Collection<PostavkaTovara> postavkatovara) {
        this.postavkatovara = postavkatovara;
    }
}
