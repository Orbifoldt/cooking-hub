package com.cookinghub.recipes.model;

/**
 * For storing an ingredient of a recipe. In a recipe not only the specific type of the ingredient (e.g. sugar or onion)
 * is of importance, we also need to know the amount required. This is a mutable object.
 * @param <T> An {@link Ingredient} denoting the type of the ingredient.
 * @param <U> A {@link Unit} containing the amount of the specified ingredient
 */
public final class RecipeIngredient<T extends Ingredient, U extends Unit> {

    private final T ingredient;
    private final U amount;

    public RecipeIngredient(T ingredient, U amount){
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public T getIngredient(){
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
    public RecipeIngredient<T,U> copy(){
        return new RecipeIngredient<>((T) ingredient.copy(), (U) amount.copy());
    }

    @Override
    public String toString(){
        return amount.toString() + " " + ingredient.getName();
    }
}
