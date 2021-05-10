package com.cookinghub.recipes.controller;

import com.cookinghub.recipes.model.recipes.Recipe;
import com.cookinghub.recipes.service.RecipeService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class RecipesController {

    @Value("${recipes.datasource.name")
    private String datasourceName;

    @Autowired
    private RecipeService recipeService;

    @RequestMapping("/")
    public String index() {
        return "Hello there...";
    }

    @RequestMapping("/yep")
    public Object page2(@RequestParam(name="id") long recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        return recipe;
    }

    @RequestMapping("/createStubRecipes")
    public String createStubRecipes(){
        return recipeService.storeStubRecipes();
    }

//    @Autowired
//    public void dataSourceResolver(ApplicationContext context){
//
//    }
}
