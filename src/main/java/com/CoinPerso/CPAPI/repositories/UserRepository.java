package com.CoinPerso.CPAPI.repositories;

import java.util.Optional;

import com.CoinPerso.CPAPI.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
