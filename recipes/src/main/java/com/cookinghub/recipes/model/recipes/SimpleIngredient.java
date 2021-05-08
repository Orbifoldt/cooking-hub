package com.cookinghub.recipes.model.recipes;

/**
 * An implementation of the {@link Ingredient} interface for storing the basic information of an ingredient used in cooking or baking.
 */
public class SimpleIngredient implements Ingredient{

    private final String name;
    private final long id;


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
