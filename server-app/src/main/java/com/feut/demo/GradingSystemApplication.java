package com.feut.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.feut"})
@EntityScan("com.feut")
public class GradingSystemApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GradingSystemApplication.class);
		app.setDefaultProperties(Collections
		          .singletonMap("server.port", "8082"));
		        app.run(args);
	}

}

