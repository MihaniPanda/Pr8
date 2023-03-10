package com.example.demoModels.Repo;

import com.example.demoModels.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUsername(String username);

    User findByUsername(String username);
}
