package com.example.demoModels.Repo;

import com.example.demoModels.Models.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, Long> {

    List<Type> findAll();
    Type findByNamee (String namee);
}
