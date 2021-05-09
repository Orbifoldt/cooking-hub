package com.cookinghub.recipes.model.recipes;

import static org.junit.Assert.assertEquals;

import com.cookinghub.recipes.model.recipes.Ingredient;
import com.cookinghub.recipes.model.recipes.SimpleIngredient;
import org.junit.Test;

public class SimpleIngredientTest {

    @Test
    public void createSimpleIngredientTest(){
        long id = 13579L;
        String ingredientName = "Onion";
        SimpleIngredient ingredient = new SimpleIngredient(ingredientName, id);
        assertEquals(ingredientName, ingredient.getName());
        assertEquals(id, ingredient.getId());
    }

    @Test
    public void copyIngredientTest(){
        long id = 13579L;
        Ingredient sugar1 = new SimpleIngredient("Sugar", id);
        Ingredient sugar2 = sugar1.copy();
        assertEquals(sugar1.getName(), sugar2.getName());
    }
    
}
