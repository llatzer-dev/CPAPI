package com.CoinPerso.CPAPI.repositories;

import com.CoinPerso.CPAPI.models.Portfolio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PortfolioRepositoy extends MongoRepository<Portfolio, String> {
    Boolean existsByIdUser(String userId);
    Optional<Portfolio> getPortfolioByIdUser(String userId);

    void deleteByIdUser(String id);
}
