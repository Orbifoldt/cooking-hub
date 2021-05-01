package com.cookinghub.recipes.service.serviceImpl;

import com.cookinghub.recipes.model.*;
import com.cookinghub.recipes.service.RecipeService;

import java.util.Arrays;
import java.util.List;

public class RecipeServiceImpl implements RecipeService {



    @Override
    public String getMessage() {
        return "This is a message from the RecipeServiceImpl!";
    }

    @Override
    public Recipe getRecipe(long id) {
        Recipe recipe;
        if(id==1L){
            recipe = getSoupRecipe();
        } else if (id == 2L){
            recipe = getCakeRecipe();
        } else {
            recipe = new SimpleRecipe("Empty Recipe");
        }
        return recipe;
    }

    private Recipe getSoupRecipe(){
        Ingredient onionIngredient = new SimpleIngredient("Onion");
        Mass onionMass = new Mass(250.);
        RecipeIngredient<Ingredient, Mass> onion = new RecipeIngredient<>(onionIngredient, onionMass);

        Ingredient broccoliIngredient = new SimpleIngredient("Brocolli");
        Unit broccoliAmount = new Mass(400.f);
        RecipeIngredient<Ingredient, Unit> brocolli = new RecipeIngredient<>(broccoliIngredient, broccoliAmount);

        Ingredient stockIngredient = new SimpleIngredient("Vegetable stock");
        Volume stockVolume = Volume.fromCups(16);
        RecipeIngredient<Ingredient, Volume> stock = new RecipeIngredient<>(stockIngredient, stockVolume);

        String recipeName = "Soup";
        List<RecipeIngredient<? extends Ingredient, ? extends Unit>> initialIngredientList = Arrays.asList(onion, brocolli, stock);
        List<String> instructions = Arrays.asList("The first step is to do this.", "Secondly we do this.", "The end.");
        return new SimpleRecipe(recipeName, initialIngredientList, instructions);
    }

    private Recipe getCakeRecipe(){
        Ingredient sugarIngredient = new SimpleIngredient("Onion");
        Mass sugarMass = new Mass(350.);
        RecipeIngredient<Ingredient, Mass> sugar = new RecipeIngredient<>(sugarIngredient, sugarMass);

        Ingredient flourIngredient = new SimpleIngredient("Brocolli");
        Unit flourAmount = new Mass(400.);
        RecipeIngredient<Ingredient, Unit> flour = new RecipeIngredient<>(flourIngredient, flourAmount);

        Ingredient eggIngredient = new SimpleIngredient("Vegetable stock");
        Volume eggVolume = Volume.fromCups(4);
        RecipeIngredient<Ingredient, Volume> egg = new RecipeIngredient<>(eggIngredient, eggVolume);

        String recipeName = "Cake";
        List<RecipeIngredient<? extends Ingredient, ? extends Unit>> initialIngredientList = Arrays.asList(sugar, flour, egg);
        List<String> instructions = Arrays.asList("For the cake we start with this.", "Then bake the cake.", "The cake is done.");
        return new SimpleRecipe(recipeName, initialIngredientList, instructions);
    }
}
