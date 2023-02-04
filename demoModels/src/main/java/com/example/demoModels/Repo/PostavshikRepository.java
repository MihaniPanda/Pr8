package com.example.demoModels.Repo;

import org.springframework.data.repository.CrudRepository;
import com.example.demoModels.Models.Postavshik;
import java.util.List;

public interface PostavshikRepository extends CrudRepository<Postavshik, Long> {

    List<Postavshik> findAll();
}



