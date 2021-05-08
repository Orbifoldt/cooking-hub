package com.cookinghub.recipes.service.serviceImpl;

import com.cookinghub.recipes.model.recipes.*;
import com.cookinghub.recipes.service.RecipeService;

import java.util.Arrays;
import java.util.List;

public class RecipeServiceImplStub implements RecipeService {



    @Override
    public String getMessage() {
        return "This is a message from the RecipeServiceImplStub!";
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
        RecipeIngredient<Mass> onion = new RecipeIngredient<>(onionIngredient, onionMass);

        Ingredient broccoliIngredient = new SimpleIngredient("Brocolli");
        Unit broccoliAmount = new Mass(400.f);
        RecipeIngredient<Unit> brocolli = new RecipeIngredient<>(broccoliIngredient, broccoliAmount);

        Ingredient stockIngredient = new SimpleIngredient("Vegetable stock");
        Volume stockVolume = Volume.fromCups(16);
        RecipeIngredient<Volume> stock = new RecipeIngredient<>(stockIngredient, stockVolume);

        String recipeName = "Soup";
        List<RecipeIngredient<? extends Unit>> initialIngredientList = Arrays.asList(onion, brocolli, stock);
        List<String> instructions = Arrays.asList("The first step is to do this.", "Secondly we do this.", "The end.");
        return new SimpleRecipe(recipeName, initialIngredientList, instructions);
    }

    private Recipe getCakeRecipe(){
        Ingredient sugarIngredient = new SimpleIngredient("Onion");
        Mass sugarMass = new Mass(350.);
        RecipeIngredient<Mass> sugar = new RecipeIngredient<>(sugarIngredient, sugarMass);

        Ingredient flourIngredient = new SimpleIngredient("Brocolli");
        Unit flourAmount = new Mass(400.);
        RecipeIngredient<Unit> flour = new RecipeIngredient<>(flourIngredient, flourAmount);

        Ingredient eggIngredient = new SimpleIngredient("Vegetable stock");
        Volume eggVolume = Volume.fromCups(4);
        RecipeIngredient<Volume> egg = new RecipeIngredient<>(eggIngredient, eggVolume);

        String recipeName = "Cake";
        List<RecipeIngredient<? extends Unit>> initialIngredientList = Arrays.asList(sugar, flour, egg);
        List<String> instructions = Arrays.asList("For the cake we start with this.", "Then bake the cake.", "The cake is done.");
        return new SimpleRecipe(recipeName, initialIngredientList, instructions);
    }
}
