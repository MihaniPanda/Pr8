package com.example.demoModels.Repo;

import com.example.demoModels.Models.Passport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Long> {
    List<Passport> findAll();
    Passport findByNumber(String number);
}
