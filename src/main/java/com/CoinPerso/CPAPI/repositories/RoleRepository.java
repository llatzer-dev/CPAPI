package com.CoinPerso.CPAPI.repositories;

import java.util.Optional;

import com.CoinPerso.CPAPI.models.ERole;
import com.CoinPerso.CPAPI.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
