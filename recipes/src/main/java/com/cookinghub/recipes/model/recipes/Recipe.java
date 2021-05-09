package com.cookinghub.recipes.model.recipes;

import java.util.List;

/**
 *
 */
public interface Recipe {

    void setName(String name);
    String getName();

    default String getLongName(){
        return getName();
    }

    long getId();
    
    List<RecipeIngredient<? extends Unit>> getIngredients();
    RecipeIngredient<? extends Unit> getIngredient(int index);
    boolean addIngredient(RecipeIngredient<? extends Unit> recipeIngredient, int index);
    void addIngredient(RecipeIngredient<? extends Unit> recipeIngredient);
    
    List<RecipeInstruction> getInstructions();
    RecipeInstruction getInstruction(int index);
    boolean addInstruction(RecipeInstruction instruction, int index);
    void addInstruction(RecipeInstruction instruction);
    boolean addInstruction(String instruction, int index);
    void addInstruction(String instruction);
}
