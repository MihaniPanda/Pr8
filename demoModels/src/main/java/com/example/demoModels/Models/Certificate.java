package com.example.demoModels.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import javax.validation.constraints.*;
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE)
    @Past
    @NotNull
    private Date data;
    @Size(min=2, max=50, message ="Наименование не может быть меньше 5 букв и не может быть больше 50 букв")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
