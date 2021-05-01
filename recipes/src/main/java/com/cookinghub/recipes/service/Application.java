package com.cookinghub.recipes.service;

import com.cookinghub.recipes.service.serviceImpl.RecipeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cookinghub.recipes.controller"} )
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RecipeService recipeService(){
		return new RecipeServiceImpl();
	}

}
