package com.cookinghub.recipes.service;

import com.cookinghub.recipes.service.serviceImpl.RecipeServiceImplStub;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cookinghub.recipes.controller", "com.cookinghub.recipes.service", "com.cookinghub.recipes.model.datasource.*"} )
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
