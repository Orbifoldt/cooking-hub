package com.cookinghub.recipes.model;

import java.util.Optional;

/**
 * This interface provides all basic properties of an ingredient used in cooking or baking. One can think of for example sugar, onion, olive oil or eggs.
 *
 * This does not contain any information on the amount of a certain ingredient as this is not an inherent property of an ingredient. It does, however, provide an optionally implemented method for storing the density of an ingredient, which may be used to convert an amount with volume-units to one with mass-units, and vice versa.
 */
public interface Ingredient {
    
    String getName();

    long getId();

    /**
     * A method to retrieve the density of an ingredient for conversion between volume- and mass-units. This default implementation only returns an empty {@link Optional}.
     * @return Returns an empty Optional
     */
    default Optional<Double> getDensity(){
        return Optional.empty();
    }

    Ingredient copy();
}
