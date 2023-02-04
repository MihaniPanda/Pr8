package com.example.demoModels.Repo;

import com.example.demoModels.Models.Joj_title;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface JojTitleRepository extends CrudRepository<Joj_title, Long> {
    List<Joj_title> findAll();

    Joj_title findByNamejob(String namejob);
}