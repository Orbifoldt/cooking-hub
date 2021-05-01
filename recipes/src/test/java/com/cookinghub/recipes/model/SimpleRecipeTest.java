package com.cookinghub.recipes.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SimpleRecipeTest {
    
    private static final double delta = 1e-6;

    @Test
    public void emptySimpleRecipeTest(){
        String recipeName = "Lasagna";
        SimpleRecipe emptyRecipe = new SimpleRecipe(recipeName);
        assertEquals(recipeName, emptyRecipe.getName());
    }

    @Test
    public void addingIngredientsSimpleRecipeTest(){
        Ingredient onionIngredient = new SimpleIngredient("Onion");
        Mass onionMass = new Mass(250.);
        RecipeIngredient<Ingredient, Mass> onion = new RecipeIngredient<>(onionIngredient, onionMass);

        Ingredient broccoliIngredient = new SimpleIngredient("Brocolli");
        Unit broccoliAmount = new Mass(400.f);
        RecipeIngredient<Ingredient, Unit> broccoli = new RecipeIngredient<>(broccoliIngredient, broccoliAmount);

        Ingredient stockIngredient = new SimpleIngredient("Vegetable stock");
        Volume stockVolume = Volume.fromCups(16);
        RecipeIngredient<Ingredient, Volume> stock = new RecipeIngredient<>(stockIngredient, stockVolume);
        
        String recipeName = "Soup";
        List<RecipeIngredient<? extends Ingredient, ? extends Unit>> initialIngredientList = Arrays.asList(onion);
        SimpleRecipe soupRecipe = new SimpleRecipe(recipeName, initialIngredientList);
        assertEquals("Expected the ingredient list to have 1 element", 1, soupRecipe.getIngredientList().size());
        assertEquals(onion.getIngredient().getName(), soupRecipe.getIngredient(0).getIngredient().getName());
        assertEquals(onion.getAmount().getValue(), soupRecipe.getIngredient(0).getAmount().getValue(), delta);

        soupRecipe.addIngredient(broccoli);
        assertEquals("Expected the ingredient list to have 2 element", 2, soupRecipe.getIngredientList().size());
        assertEquals(onion.getIngredient().getName(), soupRecipe.getIngredient(0).getIngredient().getName());
        assertEquals(onion.getAmount().getValue(), soupRecipe.getIngredient(0).getAmount().getValue(), delta);
        assertEquals(broccoli.getIngredient().getName(), soupRecipe.getIngredient(1).getIngredient().getName());
        assertEquals(broccoli.getAmount().getValue(), soupRecipe.getIngredient(1).getAmount().getValue(), delta);

        soupRecipe.addIngredient(stock, 1);
        assertEquals("Expected the ingredient list to have 3 element", 3, soupRecipe.getIngredientList().size());
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
        RecipeIngredient<Ingredient, Unit> onion = new RecipeIngredient<>(new SimpleIngredient("Onion"), new Mass(250.));
        String recipeName = "Soup";
        List<RecipeIngredient<?, ?>> initialIngredientList = Arrays.asList(onion);
        
        String step1 = "This is step 1", step2 = "The second step is this", step3 = "yet another one", step4 = "and done.";
        List<String> instructions = Arrays.asList(step1, step3);
        SimpleRecipe soupRecipe = new SimpleRecipe(recipeName, initialIngredientList, instructions);
        soupRecipe.addInstruction(step4);
        soupRecipe.addInstruction(step2, 1);
        // System.out.println(soupRecipe);

        assertEquals("Expected to find 4 instructions in total", 4, soupRecipe.getInstructions().size());
        assertEquals(step1, soupRecipe.getInstruction(0));
        assertEquals(step2, soupRecipe.getInstruction(1));
        assertEquals(step3, soupRecipe.getInstruction(2));
        assertEquals(step4, soupRecipe.getInstruction(3));
    }
    
}
