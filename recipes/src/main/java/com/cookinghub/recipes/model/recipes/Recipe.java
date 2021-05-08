package com.cookinghub.recipes.model.recipes;

import java.util.List;

/**
 *
 */
public interface Recipe {

    String getName();

    default String getLongName(){
        return getName();
    }

    long getId();
    
    List<RecipeIngredient<? extends Unit>> getIngredientList();
    RecipeIngredient<? extends Unit> getIngredient(int index);
    void addIngredient(RecipeIngredient<? extends Unit> recipeIngredient, int index);
    void addIngredient(RecipeIngredient<? extends Unit> recipeIngredient);
    
    List<String> getInstructions();
    String getInstruction(int index);
    void addInstruction(String instruction, int index);
    void addInstruction(String instruction);
}
