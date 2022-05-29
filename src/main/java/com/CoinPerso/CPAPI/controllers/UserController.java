package com.CoinPerso.CPAPI.controllers;

import com.CoinPerso.CPAPI.models.User;
import com.CoinPerso.CPAPI.payload.response.MessageResponse;
import com.CoinPerso.CPAPI.repositories.PortfolioRepositoy;
import com.CoinPerso.CPAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;
    private final PortfolioRepositoy portfolioRepositoy;

    @Autowired
    public UserController(UserRepository userRepository, PortfolioRepositoy portfolioRepositoy){
        this.userRepository = userRepository;
        this.portfolioRepositoy = portfolioRepositoy;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<User> adminAccess() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userRepository.deleteById(id);
        portfolioRepositoy.deleteByIdUser(id);

        return ResponseEntity.ok(new MessageResponse("User deleted succesfully!"));
    }

}
