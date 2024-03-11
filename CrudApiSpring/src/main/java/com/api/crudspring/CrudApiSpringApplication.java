package com.api.crudspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
public class CrudApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApiSpringApplication.class, args);
	}

}
