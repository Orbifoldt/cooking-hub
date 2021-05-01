package com.cookinghub.recipes.controller;

import com.cookinghub.recipes.model.Recipe;
import com.cookinghub.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class RecipesController {

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
}
