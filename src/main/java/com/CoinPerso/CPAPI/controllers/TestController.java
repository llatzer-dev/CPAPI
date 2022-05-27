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
    private final PortfolioRepositoy portfolioRepositoy;

    @Autowired
    private final PortfolioService portfolioService;

    @Autowired
    public TestController(RoleRepository roleRepository, UserRepository userRepository, PortfolioRepositoy portfolioRepositoy, PortfolioService portfolioService){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.portfolioRepositoy = portfolioRepositoy;
        this.portfolioService = portfolioService;
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

    @GetMapping("/portfolio/userId/{id}")
    public boolean existsPortfolioByIdUser(@PathVariable String id){
        return portfolioRepositoy.existsByIdUser(id);
    }

    @GetMapping("/portfolio/{id}")
    public ResponseEntity<Optional<Portfolio>> getPortfolioById(@PathVariable String id){
        Optional<Portfolio> portfolio;
        portfolio = portfolioRepositoy.getPortfolioByIdUser(id);

        System.out.println("----- GET PORTFOLIO -----");
        System.out.println(portfolio.toString());

        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    // @Valid en el portfolioRequest
    @PostMapping("/portfolio")
    public ResponseEntity<?> registerPortfolio(@Valid @RequestBody PortfolioRequest portfolioRequest) {

        System.out.println(portfolioRequest);

        if (!userRepository.existsById(portfolioRequest.getUserId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: That User doesn't exist!"));
        }
        /*if (portfolioRepositoy.existsByIdUser(portfolioRequest.getUserId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The User has already a portfolio!"));
        }*/

        Portfolio portfolio = portfolioRequest.getPortfolio();
        portfolio.setIdUser(portfolioRequest.getUserId());
        portfolio.setAssets(portfolioRequest.getPortfolio().getAssets());

        portfolioRepositoy.save(portfolio);

        return ResponseEntity.ok(new MessageResponse("Portfolio created successfully!"));
    }

    /*@PutMapping(value = "/clientes/{dni}", produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Cliente> modifyCliente(@PathVariable String dni, @RequestBody Cliente newCliente) {
        Cliente cliente = clienteService.modifyCliente(dni, newCliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }*/

    @PutMapping("/portfolio/{id}")
    public ResponseEntity<?> modifyPortfolio(@PathVariable String id, @RequestBody Portfolio newPortfolio){
        if (!userRepository.existsById(id)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: That User doesn't exist!"));
        }
        /*if (!portfolioRepositoy.existsByIdUser(id)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: The User doesn't have a portfolio!"));
        }*/

        Portfolio portfolio = portfolioService.modifyPortfolio(id, newPortfolio);

        /*System.out.println("DELETE PETICION");
        System.out.println("ID: " + id + " asset:" + asset.toString() );

        Optional<Portfolio> portfolio;
        portfolio = portfolioRepositoy.getPortfolioByIdUser(id);

        portfolio.get().getAssets().stream()
                .filter( a -> !a.getName().equals(asset.getName()) );


        System.out.println("DELETE RESULTADO");
        System.out.println(portfolio.toString());*/

        /*portfolioRepositoy.deleteAsset(asset.getName());*/




        /*Cliente cliente = clienteRepository.findByDni(dni).stream().findFirst().get();
        newCliente.setDni(cliente.getDni());

        return clienteRepository.save(newCliente);*/

        return ResponseEntity.ok(new MessageResponse("Asset deleted succesfully!"));
    }

}
