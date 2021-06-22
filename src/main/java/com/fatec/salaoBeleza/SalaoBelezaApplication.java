package com.fatec.salaoBeleza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.fatec"})
@EntityScan("com.fatec.model")
@EnableJpaRepositories("com.fatec.repository")
@SpringBootApplication
public class SalaoBelezaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalaoBelezaApplication.class, args);
	}
}
