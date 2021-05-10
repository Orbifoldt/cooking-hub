package com.cookinghub.recipes.service;

import com.cookinghub.recipes.model.datasource.RecipeDataSource;
import com.cookinghub.recipes.model.recipes.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

public interface RecipeService {

    public abstract String getMessage();

    public abstract Recipe getRecipe(long id);

    public abstract long createRecipe();
    public abstract void updateRecipe(Recipe recipe);
    public abstract Recipe readRecipe(long id);
    public abstract void deleteRecipe(Recipe recipe);

    String storeStubRecipes();

}
