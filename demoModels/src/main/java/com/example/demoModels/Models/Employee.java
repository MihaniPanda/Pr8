package com.example.demoModels.Models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import javax.validation.constraints.*;
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=35, message ="Фамилия не может быть меньше 2 букв и не может быть больше 35 букв")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String surname;
    @Size(min=2, max=35, message ="Имя не может быть меньше 2 букв и не может быть больше 35 букв")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String name;
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String patronymic;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull
    private Date datas;
    @Size(min=11, max=11, message ="Номер телефона должен быть длиной 11 цифр")
    private String nomertelefona;
    private boolean vishka;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="job_title_id")
    private Joj_title joj_title;

    @OneToOne(optional = true, cascade =CascadeType.ALL)
    @JoinColumn(name="passport_id")
    private Passport passport;

    @OneToOne(optional = true, cascade =CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

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

    public Date getDatas() {
        return datas;
    }

    public void setDatas(Date datas) {
        this.datas = datas;
    }

    public String getNomertelefona() {
        return nomertelefona;
    }

    public void setNomertelefona(String nomertelefona) {
        this.nomertelefona = nomertelefona;
    }

    public boolean isVishka() {
        return vishka;
    }

    public void setVishka(boolean vishka) {
        this.vishka = vishka;
    }

    public Joj_title getJoj_title() {
        return joj_title;
    }

    public void setJoj_title(Joj_title joj_title) {
        this.joj_title = joj_title;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

