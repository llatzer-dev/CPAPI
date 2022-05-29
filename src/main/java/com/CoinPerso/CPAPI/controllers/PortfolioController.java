package com.CoinPerso.CPAPI.controllers;

import com.CoinPerso.CPAPI.models.Portfolio;
import com.CoinPerso.CPAPI.payload.request.PortfolioRequest;
import com.CoinPerso.CPAPI.payload.response.MessageResponse;
import com.CoinPerso.CPAPI.repositories.PortfolioRepositoy;
import com.CoinPerso.CPAPI.repositories.RoleRepository;
import com.CoinPerso.CPAPI.repositories.UserRepository;
import com.CoinPerso.CPAPI.security.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final UserRepository userRepository;
    private final PortfolioRepositoy portfolioRepositoy;

    @Autowired
    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(UserRepository userRepository, PortfolioRepositoy portfolioRepositoy, PortfolioService portfolioService){
        this.userRepository = userRepository;
        this.portfolioRepositoy = portfolioRepositoy;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/userId/{id}")
    public boolean existsPortfolioByIdUser(@PathVariable String id){
        return portfolioRepositoy.existsByIdUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Portfolio>> getPortfolioById(@PathVariable String id){
        Optional<Portfolio> portfolio;
        portfolio = portfolioRepositoy.getPortfolioByIdUser(id);

        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> registerPortfolio(@Valid @RequestBody PortfolioRequest portfolioRequest) {
        if (!userRepository.existsById(portfolioRequest.getUserId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: That User doesn't exist!"));
        }

        Portfolio portfolio = portfolioRequest.getPortfolio();
        portfolio.setIdUser(portfolioRequest.getUserId());
        portfolio.setAssets(portfolioRequest.getPortfolio().getAssets());

        portfolioRepositoy.save(portfolio);

        return ResponseEntity.ok(new MessageResponse("Portfolio created successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPortfolio(@PathVariable String id, @RequestBody Portfolio newPortfolio){
        if (!userRepository.existsById(id)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: That User doesn't exist!"));
        }

        portfolioService.modifyPortfolio(id, newPortfolio);

        return ResponseEntity.ok(new MessageResponse("Asset deleted succesfully!"));
    }
}
