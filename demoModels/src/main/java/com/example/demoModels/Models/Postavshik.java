package com.example.demoModels.Models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Postavshik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=5, max=120, message ="Не может быть меньше 5 букв")
    private String surname;
    @Size(min=5, max=120, message ="Не может быть меньше 5 букв")
    private String name;
    @Size(min=5, max=120, message ="Не может быть меньше 5 букв")
    private String patronymic;
    @Size(min=5, max=120, message ="Не может быть меньше 5 букв")
    private String nameorganizachia;

    @OneToMany(mappedBy = "postavshik", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<PostavkaTovara> postavkatovara;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNameorganizachia() {
        return nameorganizachia;
    }

    public void setNameorganizachia(String nameorganizachia) {
        this.nameorganizachia = nameorganizachia;
    }

    public Collection<PostavkaTovara> getPostavkatovara() {
        return postavkatovara;
    }

    public void setPostavkatovara(Collection<PostavkaTovara> postavkatovara) {
        this.postavkatovara = postavkatovara;
    }
}
