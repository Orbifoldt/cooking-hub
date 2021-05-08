package com.cookinghub.recipes.model.recipes;

public class RecipeInstruction {

    private final long recipe_id;
    private String instruction;
    private int ordinal;

    public RecipeInstruction(long recipe_id, String instruction, int ordinal) {
        this.recipe_id = recipe_id;
        this.instruction = instruction;
        this.ordinal = ordinal;
    }

    public long getRecipe_id() {
        return recipe_id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
}
