package com.cookinghub.recipes.model.recipes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SimpleRecipeTest {
    
    private static final double delta = 1e-6;

    @Test
    public void emptySimpleRecipeTest(){
        String recipeName = "Lasagna";
        long id = 99L;
        SimpleRecipe emptyRecipe = new SimpleRecipe(recipeName, id);
        assertEquals(recipeName, emptyRecipe.getName());
        assertEquals(id, emptyRecipe.getId());
    }

    @Test
    public void addingIngredientsSimpleRecipeTest(){
        long recipeId = 1L;
        Ingredient onionIngredient = new SimpleIngredient("Onion", recipeId);
        Mass onionMass = new Mass(250.);
        RecipeIngredient<Mass> onion = new RecipeIngredient<>(onionIngredient, onionMass, recipeId);

        Ingredient broccoliIngredient = new SimpleIngredient("Brocolli", recipeId);
        Unit broccoliAmount = new Mass(400.f);
        RecipeIngredient<Unit> broccoli = new RecipeIngredient<>(broccoliIngredient, broccoliAmount, recipeId);

        Ingredient stockIngredient = new SimpleIngredient("Vegetable stock", recipeId);
        Volume stockVolume = Volume.fromCups(16);
        RecipeIngredient<Volume> stock = new RecipeIngredient<>(stockIngredient, stockVolume, recipeId);
        
        String recipeName = "Soup";
        List<RecipeIngredient<? extends Unit>> initialIngredientList = Arrays.asList(onion);
        SimpleRecipe soupRecipe = new SimpleRecipe(recipeName, recipeId,initialIngredientList, new ArrayList<RecipeInstruction>());
        assertEquals("Expected the ingredient list to have 1 element", 1, soupRecipe.getIngredients().size());
        assertEquals(onion.getIngredient().getName(), soupRecipe.getIngredient(0).getIngredient().getName());
        assertEquals(onion.getAmount().getValue(), soupRecipe.getIngredient(0).getAmount().getValue(), delta);

        soupRecipe.addIngredient(broccoli);
        assertEquals("Expected the ingredient list to have 2 element", 2, soupRecipe.getIngredients().size());
        assertEquals(onion.getIngredient().getName(), soupRecipe.getIngredient(0).getIngredient().getName());
        assertEquals(onion.getAmount().getValue(), soupRecipe.getIngredient(0).getAmount().getValue(), delta);
        assertEquals(broccoli.getIngredient().getName(), soupRecipe.getIngredient(1).getIngredient().getName());
        assertEquals(broccoli.getAmount().getValue(), soupRecipe.getIngredient(1).getAmount().getValue(), delta);

        soupRecipe.addIngredient(stock, 1);
        assertEquals("Expected the ingredient list to have 3 element", 3, soupRecipe.getIngredients().size());
        assertEquals(onion.getIngredient().getName(), soupRecipe.getIngredient(0).getIngredient().getName());
        assertEquals(onion.getAmount().getValue(), soupRecipe.getIngredient(0).getAmount().getValue(), delta);
        assertEquals(stock.getIngredient().getName(), soupRecipe.getIngredient(1).getIngredient().getName());
        assertEquals(stock.getAmount().getValue(), soupRecipe.getIngredient(1).getAmount().getValue(), delta);
        assertEquals(broccoli.getIngredient().getName(), soupRecipe.getIngredient(2).getIngredient().getName());
        assertEquals(broccoli.getAmount().getValue(), soupRecipe.getIngredient(2).getAmount().getValue(), delta);

        // System.out.println(soupRecipe);
    }

    @Test
    public void addingInstructionsSimpleRecipeTest(){
        String recipeName = "Soup";
        long recipeId = 15L;
        RecipeIngredient<Unit> onion = new RecipeIngredient<>(new SimpleIngredient("Onion", recipeId), new Mass(250.), recipeId);
        List<RecipeIngredient<?>> initialIngredientList = Arrays.asList(onion);

        String step1 = "This is step 1", step2 = "The second step is this", step3 = "yet another one", step4 = "and done.";
        List<RecipeInstruction> instructions = Arrays.asList(new RecipeInstruction(recipeId, 0, step1), new RecipeInstruction(recipeId, 1, step3));
        SimpleRecipe soupRecipe = new SimpleRecipe(recipeName, recipeId,initialIngredientList, instructions);
        soupRecipe.addInstruction(step4);
        soupRecipe.addInstruction(step2, 1);
        // System.out.println(soupRecipe);

        assertEquals("Expected to find 4 instructions in total", 4, soupRecipe.getInstructions().size());
        assertEquals(step1, soupRecipe.getInstruction(0).getInstruction());
        assertEquals(step2, soupRecipe.getInstruction(1).getInstruction());
        assertEquals(step3, soupRecipe.getInstruction(2).getInstruction());
        assertEquals(step4, soupRecipe.getInstruction(3).getInstruction());
    }
    
}
