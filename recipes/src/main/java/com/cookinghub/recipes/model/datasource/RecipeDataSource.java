package com.cookinghub.recipes.model.datasource;

import com.cookinghub.recipes.model.recipes.*;

public interface RecipeDataSource {

    Recipe getRecipe(long id);

    Ingredient getIngredient(long id);



    Recipe storeNewRecipe();
    
    void updateRecipe(Recipe recipe);



    Ingredient storeNewIngredient();

    void updateIngredient(Ingredient ingredient);



    void storeNewRecipeIngredient(long recipeId, long ingredientId, int ordinal, double amount, Unit.UnitType unitType);

    default void storeNewRecipeIngredient(long recipeId, long ingredientId, int ordinal, double amount){
        storeNewRecipeIngredient(recipeId, ingredientId, ordinal, amount, Unit.UnitType.OTHER);
    }

    void updateRecipeIngredient(RecipeIngredient<? extends Unit>  recipeIngredient);



    void storeNewRecipeInstruction(long recipeId, int ordinal, String instruction);

    void updateRecipeInstruction(RecipeInstruction recipeInstruction);


    
}
