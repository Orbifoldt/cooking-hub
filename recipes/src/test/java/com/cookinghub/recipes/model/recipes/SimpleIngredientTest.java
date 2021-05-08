package com.cookinghub.recipes.model.recipes;

import static org.junit.Assert.assertEquals;

import com.cookinghub.recipes.model.recipes.Ingredient;
import com.cookinghub.recipes.model.recipes.SimpleIngredient;
import org.junit.Test;

public class SimpleIngredientTest {

    @Test
    public void createSimpleIngredientTest(){
        String ingredientName = "Onion";
        SimpleIngredient ingredient = new SimpleIngredient(ingredientName);
        assertEquals(ingredientName, ingredient.getName());
    }

    @Test
    public void copyIngredientTest(){
        Ingredient sugar1 = new SimpleIngredient("Sugar");
        Ingredient sugar2 = sugar1.copy();
        assertEquals(sugar1.getName(), sugar2.getName());
    }
    
}
