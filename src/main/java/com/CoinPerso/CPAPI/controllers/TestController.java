package com.CoinPerso.CPAPI.controllers;

import com.CoinPerso.CPAPI.models.Portfolio;
import com.CoinPerso.CPAPI.models.Role;
import com.CoinPerso.CPAPI.models.User;
import com.CoinPerso.CPAPI.payload.request.PortfolioRequest;
import com.CoinPerso.CPAPI.payload.response.MessageResponse;
import com.CoinPerso.CPAPI.repositories.PortfolioRepositoy;
import com.CoinPerso.CPAPI.repositories.RoleRepository;
import com.CoinPerso.CPAPI.repositories.UserRepository;
import com.CoinPerso.CPAPI.security.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public TestController(RoleRepository roleRepository, UserRepository userRepository, PortfolioRepositoy portfolioRepositoy, PortfolioService portfolioService){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
