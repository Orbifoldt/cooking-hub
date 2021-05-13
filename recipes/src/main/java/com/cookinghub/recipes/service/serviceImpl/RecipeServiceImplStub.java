package com.cookinghub.recipes.service.serviceImpl;

import com.cookinghub.recipes.model.recipes.*;
import com.cookinghub.recipes.service.RecipeService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RecipeServiceImplStub implements RecipeService {



    @Override
    public String getMessage() {
        return "This is a message from the RecipeServiceImplStub!";
    }

    @Override
    public Recipe getRecipe(long id) {
        Recipe recipe;
        if(id==1L){
            recipe = getSoupRecipe(1L);
        } else if (id == 2L){
            recipe = getCakeRecipe(2L);
        } else {
            recipe = new SimpleRecipe("New Empty Recipe", id);
        }
        return recipe;
    }

    @Override
    public long createRecipe() {
        return 0;
    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }

    @Override
    public Recipe readRecipe(long id) {
        return null;
    }

    @Override
    public void deleteRecipe(Recipe recipe) {

    }

    @Override
    public String storeStubRecipes() {
        return null;
    }

    public Recipe getSoupRecipe(long id){
        Ingredient onionIngredient = new SimpleIngredient("Onion", id);
        Mass onionMass = new Mass(250.);
        RecipeIngredient<Mass> onion = new RecipeIngredient<>(onionIngredient, onionMass, id);

        Ingredient broccoliIngredient = new SimpleIngredient("Brocolli", id);
        Unit broccoliAmount = new Mass(400.f);
        RecipeIngredient<Unit> brocolli = new RecipeIngredient<>(broccoliIngredient, broccoliAmount, id);

        Ingredient stockIngredient = new SimpleIngredient("Vegetable stock", id);
        Volume stockVolume = Volume.fromCups(16);
        RecipeIngredient<Volume> stock = new RecipeIngredient<>(stockIngredient, stockVolume, id);

        String recipeName = "Soup";
        List<RecipeIngredient<? extends Unit>> initialIngredientList = Arrays.asList(onion, brocolli, stock);
        RecipeInstruction step1 = new RecipeInstruction(id, 0, "The first step is to do this.");
        RecipeInstruction step2 = new RecipeInstruction(id, 1, "Secondly we do this.");
        RecipeInstruction step3 = new RecipeInstruction(id, 2, "The end.");
        List<RecipeInstruction> instructions = Arrays.asList(step1, step2, step3);
        return new SimpleRecipe(recipeName, id, initialIngredientList, instructions);
    }

    public Recipe getCakeRecipe(long id){
        Ingredient sugarIngredient = new SimpleIngredient("Onion", id);
        Mass sugarMass = new Mass(350.);
        RecipeIngredient<Mass> sugar = new RecipeIngredient<>(sugarIngredient, sugarMass, id);

        Ingredient flourIngredient = new SimpleIngredient("Brocolli", id);
        Unit flourAmount = new Mass(400.);
        RecipeIngredient<Unit> flour = new RecipeIngredient<>(flourIngredient, flourAmount, id);

        Ingredient eggIngredient = new SimpleIngredient("Vegetable stock", id);
        Volume eggVolume = Volume.fromCups(4);
        RecipeIngredient<Volume> egg = new RecipeIngredient<>(eggIngredient, eggVolume, id);

        String recipeName = "Cake";
        List<RecipeIngredient<? extends Unit>> initialIngredientList = Arrays.asList(sugar, flour, egg);
        RecipeInstruction step1 = new RecipeInstruction(id, 0, "For the cake we start with this.");
        RecipeInstruction step2 = new RecipeInstruction(id, 1, "Then bake the cake.");
        RecipeInstruction step3 = new RecipeInstruction(id, 2, "The cake is done.");
        List<RecipeInstruction> instructions = Arrays.asList(step1, step2, step3);
        return new SimpleRecipe(recipeName, id, initialIngredientList, instructions);
    }
}
