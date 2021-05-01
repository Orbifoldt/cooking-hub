package com.cookinghub.recipes.model;

import java.util.List;

public interface Recipe {

    String getName();

    default String getLongName(){
        return getName();
    }

    long getId();
    
    List<RecipeIngredient<? extends Ingredient, ? extends Unit>> getIngredientList();
    RecipeIngredient<? extends Ingredient, ? extends Unit> getIngredient(int index);
    void addIngredient(RecipeIngredient<? extends Ingredient, ? extends Unit> recipeIngredient, int index);
    void addIngredient(RecipeIngredient<? extends Ingredient, ? extends Unit> recipeIngredient);
    
    List<String> getInstructions();
    String getInstruction(int index);
    void addInstruction(String instruction, int index);
    void addInstruction(String instruction);
}
