package com.cookinghub.recipes.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RecipeIngredientTest {

    @Test
    public void creatingRecipeIngredientMassTest(){
        String ingredientName = "Sugar";
        double gram = 100.;
        Ingredient sugar = new SimpleIngredient(ingredientName);
        Mass weight = new Mass(gram);
        RecipeIngredient<Ingredient, Mass> ri = new RecipeIngredient<>(sugar, weight);
        assertEquals(ingredientName, ri.getIngredient().getName());
        assertEquals(gram, ri.getAmount().getGram(), 1e-6);
    }

    @Test
    public void creatingRecipeIngredientVolumeTest(){
        String ingredientName = "Water";
        double milliliter = 100.;
        Ingredient water = new SimpleIngredient(ingredientName);
        Volume weight = new Volume(milliliter);
        RecipeIngredient<Ingredient, Volume> ri = new RecipeIngredient<>(water, weight);
        assertEquals(ingredientName, ri.getIngredient().getName());
        assertEquals(milliliter, ri.getAmount().getMilliliter(), 1e-6);
    }



    @Test
    public void copyRecipeIngredientTest(){
        String ingredientName = "Sugar";
        double gram = 100.;
        Ingredient sugar = new SimpleIngredient(ingredientName);
        Mass weight = new Mass(gram);
        RecipeIngredient<Ingredient, Mass> ri = new RecipeIngredient<>(sugar, weight);
        RecipeIngredient<Ingredient, Mass> riCopy = ri.copy();
        assertEquals(ri.getIngredient().getName(), riCopy.getIngredient().getName());
        assertEquals(ri.getAmount().getGram(), riCopy.getAmount().getGram() ,1e-6);
    }
    
}
