package com.CoinPerso.CPAPI.security.services;

import com.CoinPerso.CPAPI.models.Portfolio;
import com.CoinPerso.CPAPI.repositories.PortfolioRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepositoy portfolioRepositoy;

    @Override
    public Portfolio modifyPortfolio(String id, Portfolio newPortfolio) {
        Portfolio portfolio = portfolioRepositoy.getPortfolioByIdUser(id).stream().findFirst().get();
        newPortfolio.setId(portfolio.getId());

        return portfolioRepositoy.save(newPortfolio);
    }
}
