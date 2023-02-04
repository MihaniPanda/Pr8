package com.example.demoModels.Repo;

import com.example.demoModels.Models.Proizvoditel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProizvoditelRepository extends CrudRepository<Proizvoditel, Long> {
    List<Proizvoditel> findAll();

    Proizvoditel findByName(String Name);
}
