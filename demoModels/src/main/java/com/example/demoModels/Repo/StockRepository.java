package com.example.demoModels.Repo;


import com.example.demoModels.Models.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findAll();
    Stock findByAdress(String adress);
}
