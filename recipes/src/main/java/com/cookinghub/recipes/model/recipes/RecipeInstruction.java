package com.cookinghub.recipes.model.recipes;

public final class RecipeInstruction implements Comparable<RecipeInstruction> {

    private final long recipeId;
    private String instruction;
    private int ordinal;

    /**
     * This is an instruction that is part of a specific recipe (associated to the recipeId).
     * @param recipeId The unique identifier pointing to the recipes this instruction is a part of.
     * @param ordinal The ordinal of this instruction in the list of all instruction, i.e. this number determines the
     *                order in which the various instructions associated to a single recipe appear.
     * @param instruction The actual instruction, e.g. "Add the flour and knead for 3 minutes."
     */
    public RecipeInstruction(long recipeId, int ordinal, String instruction) {
        this.recipeId = recipeId;
        this.instruction = instruction;
        this.ordinal = ordinal;
    }

    public long getRecipeId() {
        return recipeId;
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


    @Override
    public String toString(){
        return ordinal + ". " + instruction;
    }

    @Override
    public int compareTo(RecipeInstruction other) {
        if(recipeId == other.getRecipeId()){
            return ordinal - other.getOrdinal();
        }
        return recipeId < other.getRecipeId() ? -1 : 1;
    }
}
