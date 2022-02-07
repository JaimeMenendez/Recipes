package com.jaime.recipes.repository;

import org.springframework.data.repository.CrudRepository;
import com.jaime.recipes.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);
    boolean existsByEmail(String email);
}
