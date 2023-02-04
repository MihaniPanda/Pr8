package com.example.demoModels.Repo;
import com.example.demoModels.Models.Certificate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CertificateRepository  extends CrudRepository<Certificate, Long> {
    List<Certificate> findAll();
    Certificate findByName(String name);
}
