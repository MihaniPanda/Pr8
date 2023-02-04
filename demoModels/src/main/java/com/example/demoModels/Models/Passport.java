package com.example.demoModels.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min=6, max=6, message ="Номер паспорта должен быть длиной 6 цифр")
    private String number;
    @Size(min=4, max=4, message ="Серия паспорта должен быть длиной 4 цифры")
    private String series;
    @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}