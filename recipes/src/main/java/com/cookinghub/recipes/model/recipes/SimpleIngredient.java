package com.cookinghub.recipes.model.recipes;

import java.util.Optional;

/**
 * An implementation of the {@link Ingredient} interface for storing the basic information of an ingredient used in cooking or baking.
 */
public class SimpleIngredient implements Ingredient{

    private final String name;
    private final long id;
    private Optional<Double> density = Optional.empty();

    public SimpleIngredient(long id){
        this("NO_NAME", id);
    }

    /**
     * Creates a SimpleIngredient with a given name.
     * @param name The name of the ingredient
     */
    public SimpleIngredient(String name){
        this(name, 0L);
    }

    /**
     * Creates a SimpleIngredient with a given name.
     * @param name The name of the ingredient
     */
    public SimpleIngredient(String name, long id){
        this.name = name;
        this.id = id;
    }

    /**
     * Creates a SimpleIngredient with a given name.
     * @param name The name of the ingredient
     */
    public SimpleIngredient(String name, long id, Optional<Double> density){
        this.name = name;
        this.id = id;
        this.density = density;
    }

    @Override
    public Optional<Double> getDensity() {
        return density;
    }

    public void setDensity(Optional<Double> density) {
        this.density = density;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Ingredient copy() {
        return new SimpleIngredient(name);
    }
    
}
