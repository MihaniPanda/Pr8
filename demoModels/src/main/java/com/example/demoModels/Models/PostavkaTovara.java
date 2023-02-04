package com.example.demoModels.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PostavkaTovara {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentariy;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stock_id")
    private Stock stock;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="postavshik_id")
    private Postavshik postavshik;

    @ManyToMany
    @JoinTable(name="TovarPostavkiTovara",
            joinColumns=@JoinColumn(name="postavkatovara_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> Pods = new HashSet<Product>();


}
