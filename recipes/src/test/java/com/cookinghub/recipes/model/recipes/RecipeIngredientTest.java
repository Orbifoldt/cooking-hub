package com.cookinghub.recipes.model.recipes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RecipeIngredientTest {

    private long melonRecipeId = 98765L;
    private Mass weight = new Mass(500);
    private Ingredient melon = new SimpleIngredient("Melon", melonRecipeId);
    
    @Test
    public void creatingRecipeIngredientMassTest(){
        long id = 13579L;
        String ingredientName = "Sugar";
        double gram = 100.;
        Ingredient sugar = new SimpleIngredient(ingredientName, id);
        Mass weight = new Mass(gram);
        RecipeIngredient<Mass> ri = new RecipeIngredient<>(sugar, weight, id);
        assertEquals(ingredientName, ri.getIngredient().getName());
        assertEquals(gram, ri.getAmount().getGram(), 1e-6);
    }

    @Test
    public void creatingRecipeIngredientVolumeTest(){
        long id = 13579L;
        String ingredientName = "Water";
        double milliliter = 100.;
        Ingredient water = new SimpleIngredient(ingredientName, id);
        Volume weight = new Volume(milliliter);
        RecipeIngredient<Volume> ri = new RecipeIngredient<>(water, weight, id);
        assertEquals(ingredientName, ri.getIngredient().getName());
        assertEquals(milliliter, ri.getAmount().getMilliliter(), 1e-6);
    }

    @Test
    public void copyRecipeIngredientTest(){
        long id = 13579L;
        String ingredientName = "Sugar";
        double gram = 100.;
        Ingredient sugar = new SimpleIngredient(ingredientName, id);
        Mass weight = new Mass(gram);
        RecipeIngredient<Mass> ri = new RecipeIngredient<>(sugar, weight, id);
        RecipeIngredient<Mass> riCopy = ri.copy();
        assertEquals(ri.getIngredient().getName(), riCopy.getIngredient().getName());
        assertEquals(ri.getAmount().getGram(), riCopy.getAmount().getGram() ,1e-6);
    }

    @Test
    public void recipeIngredientGettersSettersTest(){
        RecipeIngredient<Mass> ri = new RecipeIngredient<>(melon, weight,1337L,15);
        ri.setNotes("This is a note");
        assertEquals(1337L, ri.getRecipeId());
        assertEquals(15, ri.getOrdinal());
        assertEquals("This is a note", ri.getNotes());

        ri.setOrdinal(800);
        ri.setNotes("A changed note...");
        assertEquals(1337L, ri.getRecipeId());
        assertEquals(800, ri.getOrdinal());
        assertEquals("A changed note...", ri.getNotes());
    }

    @Test
    public void recipeIngredientComparableTestEqual(){
        RecipeIngredient<Mass> ingredient = new RecipeIngredient<>(melon, weight,666L, 9);
        int comp = ingredient.compareTo(ingredient);
        assertEquals("The ingredient should equal itself.", 0, comp);
    }

    @Test
    public void recipeIngredientComparableTestSmallerOrdinal(){
        RecipeIngredient<Mass> ingredient1 = new RecipeIngredient<>(melon, weight,123L, 8);
        RecipeIngredient<Mass> ingredient2 = new RecipeIngredient<>(melon, weight,123L, 9);
        int comp = ingredient1.compareTo(ingredient2);
        assertTrue("Expected ingredient1 to be smaller.", comp < 0);
        assertEquals("Expected switched comparison to give minus the original answer", -comp, ingredient2.compareTo(ingredient1));
    }

    @Test
    public void recipeIngredientComparableTestSmallerId(){
        RecipeIngredient<Mass> ingredient1 = new RecipeIngredient<>(melon, weight,5L, 1);
        RecipeIngredient<Mass> ingredient2 = new RecipeIngredient<>(melon, weight,6L, 1);
        int comp = ingredient1.compareTo(ingredient2);
        assertTrue("Expected ingredient1 to be smaller.", comp < 0);
        assertEquals("Expected switched comparison to give minus the original answer", -comp, ingredient2.compareTo(ingredient1));
    }

    @Test
    public void recipeIngredientComparableTestTransitivityOrdinal(){
        RecipeIngredient<Mass> ingredient1 = new RecipeIngredient<>(melon, weight,10L, 1);
        RecipeIngredient<Mass> ingredient2 = new RecipeIngredient<>(melon, weight,10L, 2);
        RecipeIngredient<Mass> ingredient3 = new RecipeIngredient<>(melon, weight,10L, 3);
        int comp12 = ingredient1.compareTo(ingredient2);
        int comp13 = ingredient1.compareTo(ingredient3);
        int comp23 = ingredient2.compareTo(ingredient3);
        assertTrue(comp12 < 0);
        assertTrue(comp23 < 0);
        assertTrue("Found that 1<2 and 2<3 but NOT 1<3",comp13 < 0);
    }

    @Test
    public void recipeIngredientComparableTestTransitivityId(){
        RecipeIngredient<Mass> ingredient1 = new RecipeIngredient<>(melon, weight,1L, 10);
        RecipeIngredient<Mass> ingredient2 = new RecipeIngredient<>(melon, weight,2L, 10);
        RecipeIngredient<Mass> ingredient3 = new RecipeIngredient<>(melon, weight,3L, 10);
        int comp12 = ingredient1.compareTo(ingredient2);
        int comp13 = ingredient1.compareTo(ingredient3);
        int comp23 = ingredient2.compareTo(ingredient3);
        assertTrue(comp12 < 0);
        assertTrue(comp23 < 0);
        assertTrue("Found that 1<2 and 2<3 but NOT 1<3",comp13 < 0);
    }
    
}
