package com.cookinghub.recipes.model.recipes;

/**
 * For storing an ingredient of a recipe. In a recipe not only the specific type of the ingredient (e.g. sugar or onion)
 * is of importance, we also need to know the amount required. This is a mutable object.
 * @param <U> A {@link Unit} containing the amount of the specified ingredient
 */
public final class RecipeIngredient<U extends Unit> implements Comparable<RecipeIngredient<? extends Unit>>, Copyable<RecipeIngredient<U>>{

    private final Ingredient ingredient;
    private final U amount;

    private final long recipeId;
    private String notes = "";
    private int ordinal;

    public RecipeIngredient(Ingredient ingredient, U amount, long recipeId){
        this(ingredient, amount, recipeId, -1);
    }

    public RecipeIngredient(Ingredient ingredient, U amount, long recipeId, int ordinal){
        this.ingredient = ingredient;
        this.amount = amount;
        this.recipeId = recipeId;
        this.ordinal = ordinal;
    }

    public Ingredient getIngredient(){
        return ingredient;
    }

    public U getAmount(){
        return amount;
    }

    /**
     * Create a copy by invoking the copy() methods on the ingredient and amount of this RecipeIngredient
     * @return A copy of this
     */
    @SuppressWarnings("unchecked")
    public RecipeIngredient<U> copy(){
        RecipeIngredient<U> recipeIngredient = new RecipeIngredient<>(ingredient.copy(), (U) amount.copy(), recipeId, ordinal);
        recipeIngredient.setNotes(notes);
        return recipeIngredient;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public String toString(){
        return amount.toString() + " " + ingredient.getName() + "(" + notes + ")";
    }


    @Override
    public int compareTo(RecipeIngredient<? extends Unit> other) {
        if(recipeId == other.getRecipeId()){
            return ordinal - other.getOrdinal();
        }
        return recipeId < other.getRecipeId() ? -1 : 1;
    }
}
