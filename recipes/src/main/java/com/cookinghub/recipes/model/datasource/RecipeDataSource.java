package com.cookinghub.recipes.model.datasource;

import com.cookinghub.recipes.model.recipes.*;

import java.util.List;

public interface RecipeDataSource {







    Recipe storeNewRecipe();
    
    void updateRecipe(Recipe recipe);

    Recipe getRecipe(long id);

    void deleteRecipe(long id);



    Ingredient storeNewIngredient();

    void updateIngredient(Ingredient ingredient);

    Ingredient getIngredient(long id);

    void deleteIngredient(long id);



    void storeNewRecipeIngredient(long recipeId, long ingredientId, int ordinal, double amount, Unit.UnitType unitType);

    default void storeNewRecipeIngredient(long recipeId, long ingredientId, int ordinal, double amount){
        storeNewRecipeIngredient(recipeId, ingredientId, ordinal, amount, Unit.UnitType.OTHER);
    }

    void updateRecipeIngredient(RecipeIngredient<? extends Unit>  recipeIngredient);

    List<RecipeIngredient<? extends Unit>> getRecipeIngredients(long recipeId);

    void deleteRecipeIngredient(long recipeId, int ordinal);



    void storeNewRecipeInstruction(long recipeId, int ordinal, String instruction);

    void updateRecipeInstruction(RecipeInstruction recipeInstruction);

    List<RecipeInstruction> getRecipeInstructions(long recipeId);

    void deleteRecipeInstruction(long recipeId, int ordinal);


    
}
