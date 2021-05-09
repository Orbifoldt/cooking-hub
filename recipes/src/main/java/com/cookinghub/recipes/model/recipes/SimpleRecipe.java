package com.cookinghub.recipes.model.recipes;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecipe implements Recipe{

    private final String name;
    private final long id;

    private List<RecipeIngredient<? extends Unit>> ingredientList = new ArrayList<>();
    private List<String> instructions = new ArrayList<>();

    public SimpleRecipe(long id){
        this("NO_NAME", id);
    }

    public SimpleRecipe(String name){
        this(name, 0L);
    }

    public SimpleRecipe(String name, long id){
        this.name = name;
        this.id = id;
    }

    public SimpleRecipe(String name,  List<RecipeIngredient<? extends Unit>> ingredientList){
        this(name);
        this.ingredientList = copyIngredients(ingredientList);
    }

    public SimpleRecipe(String name,  
                        List<RecipeIngredient<? extends Unit>> ingredientList,
                        List<String> instructions)
    {
        this(name, ingredientList);
        this.instructions = new ArrayList<>(instructions);
    }

    @Override
    public List<RecipeIngredient<? extends Unit>> getIngredientList() {
        return copyIngredients(ingredientList);
    }

    @Override
    public RecipeIngredient<? extends Unit> getIngredient(int index) {
        return ingredientList.get(index);
    }

    @Override
    public void addIngredient(RecipeIngredient<? extends Unit> recipeIngredient, int index) {
        ingredientList.add(index, recipeIngredient);
    }

    @Override
    public void addIngredient(RecipeIngredient<? extends Unit> recipeIngredient) {
        ingredientList.add(recipeIngredient);
    }

    private List<RecipeIngredient<? extends Unit>> copyIngredients(List<RecipeIngredient<? extends Unit>> sourceIngredientList){
        List<RecipeIngredient<? extends Unit>> copiedIngredientList = new ArrayList<>();
        for(RecipeIngredient<? extends Unit> recipeIngredient : sourceIngredientList){
            copiedIngredientList.add(recipeIngredient.copy());
        }
        return copiedIngredientList;
    }

    @Override
    public List<String> getInstructions() {
        return new ArrayList<>(instructions);
    }

    @Override
    public String getInstruction(int index) {
        return instructions.get(index);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addInstruction(String instruction, int index) {
        instructions.add(index, instruction);
        
    }

    @Override
    public void addInstruction(String instruction) {
        instructions.add(instruction);        
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("===== Ingredients =====");
        for(var ingredient : ingredientList){
            output.append("\n - ").append(ingredient.toString());
        }
        output.append("\n\n===== Instructions =====");
        int n = 0;
        for(String instruction : instructions){
            output.append("\n ").append(++n).append(". ").append(instruction);
        }
        return output.toString();
    }
    
}
