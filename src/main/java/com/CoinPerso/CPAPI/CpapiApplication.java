package com.CoinPerso.CPAPI;

import com.CoinPerso.CPAPI.models.User;
import com.CoinPerso.CPAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CpapiApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	@Autowired
	public CpapiApplication(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CpapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		if (userRepository.findAll().isEmpty()){
			userRepository.save(new User("Alice", "Smith"));
			userRepository.save(new User("Bob", "Smith"));
		}

		for (User user: userRepository.findAll()){
			System.out.println(user);
		}
	}
}
