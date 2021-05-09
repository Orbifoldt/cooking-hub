package com.cookinghub.recipes.model.recipes;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SimpleRecipe implements Recipe{

    private final long id;

    private String name;
    private LinkedHashSet<RecipeIngredient<? extends Unit>> ingredients = new LinkedHashSet<>();
    private LinkedHashSet<RecipeInstruction> instructions = new LinkedHashSet<>();


    public SimpleRecipe(String name, long id){
        this.name = name;
        this.id = id;
    }

    public SimpleRecipe(long id){
        this("NO_NAME", id);
    }

    public SimpleRecipe(String name,
                        long id,
                        List<RecipeIngredient<? extends Unit>> ingredientList,
                        List<RecipeInstruction> instructions) {
        this(name, id);
        this.ingredients = copyIngredientsFromList(ingredientList);
        this.instructions = copyInstructionsFromList(instructions);
        updateIngredientOrdinals();
        updateInstructionOrdinals();
    }

    public SimpleRecipe(SimpleRecipe recipe){
        this(recipe.getName(), recipe.getId(), recipe.getIngredients(), recipe.getInstructions());
    }

    private void updateIngredientOrdinals(){
        int i = 0;
        for(RecipeIngredient<?> ingredient : ingredients){
            ingredient.setOrdinal(i++);
            assert ingredient.getRecipeId() == id;
        }
    }

    private void updateInstructionOrdinals(){
        int i = 0;
        for(RecipeInstruction instruction: instructions){
            instruction.setOrdinal(i++);
            assert instruction.getRecipeId() == id;
        }
    }


    private LinkedHashSet<RecipeIngredient<? extends Unit>> copyIngredientsFromList(List<RecipeIngredient<? extends Unit>> sourceList) {
        LinkedHashSet<RecipeIngredient<? extends Unit>> outputSet = new LinkedHashSet<>();
        for(RecipeIngredient<?> recipeIngredient : sourceList){
            outputSet.add(recipeIngredient.copy());
        }
        return outputSet;
    }

    private LinkedHashSet<RecipeInstruction> copyInstructionsFromList(List<RecipeInstruction> sourceList) {
        LinkedHashSet<RecipeInstruction> outputSet = new LinkedHashSet<>();
        for(RecipeInstruction recipeInstruction : sourceList){
            outputSet.add(recipeInstruction.copy());
        }
        return outputSet;
    }


    private Predicate<RecipeIngredient<? extends Unit>> hasIngredientAt(final int index){
        return ingredient -> ingredient.getOrdinal() == index;
    }

    private Predicate<RecipeInstruction> hasInstructionAt(final int index){
        return instruction -> instruction.getOrdinal() == index;
    }


    @Override
    public List<RecipeIngredient<? extends Unit>> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    @Override
    public RecipeIngredient<? extends Unit> getIngredient(int index) throws NoSuchElementException {
        return ingredients.stream().filter(hasIngredientAt(index)).findFirst().get();
    }

    @Override
    public boolean addIngredient(RecipeIngredient<? extends Unit> recipeIngredient, int index) {
        int numIngredients = ingredients.size();
        if(index >= 0 && index <= numIngredients){
            if (index < numIngredients) {
                assert ingredients.stream().anyMatch(hasIngredientAt(index));
                LinkedList<RecipeIngredient<?>> list = new LinkedList<>(ingredients);
                for (int i = list.size() - 1; i >= index; i--) {
                    RecipeIngredient<?> otherIngredient = list.get(i);
                    assert otherIngredient.getOrdinal() == i;
                    otherIngredient.setOrdinal(i + 1);
                }
            }
            recipeIngredient.setOrdinal(index);
            return ingredients.add(recipeIngredient);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void addIngredient(RecipeIngredient<? extends Unit> recipeIngredient) {
        addIngredient(recipeIngredient, ingredients.size());
    }

    @Override
    public List<RecipeInstruction> getInstructions() {
        return new ArrayList<>(instructions);
    }

    @Override
    public RecipeInstruction getInstruction(int index) throws NoSuchElementException {
        return instructions.stream().filter(hasInstructionAt(index)).findFirst().get();
    }

    @Override
    public boolean addInstruction(RecipeInstruction instruction, int index) {
        int numInstructions = instructions.size();
        if(index >=0 && index <= numInstructions){
            if(index < numInstructions){
                assert instructions.stream().anyMatch(hasInstructionAt(index));
                LinkedList<RecipeInstruction> list = new LinkedList<>(instructions);
                for (int i = list.size()-1; i>= index; i--){
                    RecipeInstruction otherInstruction = list.get(i);
                    assert otherInstruction.getOrdinal() == i;
                    otherInstruction.setOrdinal(i + 1);
                }
            }
            instruction.setOrdinal(index);
            return instructions.add(instruction);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void addInstruction(RecipeInstruction instruction) {
        addInstruction(instruction, instructions.size());
    }

    @Override
    public boolean addInstruction(String instruction, int index) {
        return addInstruction(new RecipeInstruction(id, index, instruction), index);
    }

    @Override
    public void addInstruction(String instruction) {
        addInstruction(instruction, instructions.size());
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("===== Ingredients =====");
        for(var ingredient : ingredients){
            output.append("\n - ").append(ingredient.toString());
        }
        output.append("\n\n===== Instructions =====");
        int n = 0;
        for(RecipeInstruction instruction : instructions){
            output.append("\n ").append(++n).append(". ").append(instruction.getInstruction());
        }
        return output.toString();
    }
    
}
