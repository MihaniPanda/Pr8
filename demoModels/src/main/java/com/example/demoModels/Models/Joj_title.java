package com.example.demoModels.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class Joj_title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=35, message ="Должность не может быть меньше 2 букв и не может быть больше 35 букв")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String namejob;
    @Positive(message = "Зарабатная плата должна быть больше 0")
    @NotNull(message = "Заполните заработную плату")
    private Double price;
    @OneToMany(mappedBy = "joj_title", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Employee> joj_title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamejob() {
        return namejob;
    }

    public void setNamejob(String namejob) {
        this.namejob = namejob;
    }

    public Double getPrice() {
        return price;
    }

    public void AddEmployee(Employee employee){
        this.joj_title.add(employee);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Collection<Employee> getJoj_title() {
        return joj_title;
    }

    public void setJoj_title(Collection<Employee> joj_title) {
        this.joj_title = joj_title;
    }
}
