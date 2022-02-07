package com.algafood.cursoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algafood.cursoapi.infrastructure.repository.CustomRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class AlgafoodResApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodResApiApplication.class, args);
	}

}
