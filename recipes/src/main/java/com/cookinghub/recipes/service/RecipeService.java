package com.cookinghub.recipes.service;

import com.cookinghub.recipes.model.recipes.Recipe;

public interface RecipeService {

    String getMessage();

    Recipe getRecipe(long id);
}
