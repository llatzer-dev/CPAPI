package com.CoinPerso.CPAPI.security.services;

import com.CoinPerso.CPAPI.models.Portfolio;

public interface PortfolioService {
    Portfolio modifyPortfolio(String id, Portfolio portfolio);
}
