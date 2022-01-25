package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GovtechApplication {

	public static void main(String[] args) {
		SpringApplication.run(GovtechApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			repository.save(new User("John", 3000.0));
			repository.save(new User("John 2", 3500.0));
			repository.save(new User("Mary Posa", 4000.00));
			repository.save(new User("Ryuto Sunaga", 6000.05));
			repository.save(new User("Hendrick", 3000.2));
			repository.save(new User("Mike Ross", 7000));
			repository.save(new User("Speencer Teo", 1503.2));
			repository.save(new User("Lionel Messi", 3150));
			repository.save(new User("Ronaldo", 4300));

		};
	}

}



