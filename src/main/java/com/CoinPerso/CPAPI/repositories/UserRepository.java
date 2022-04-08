package com.CoinPerso.CPAPI.repositories;

import com.CoinPerso.CPAPI.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
