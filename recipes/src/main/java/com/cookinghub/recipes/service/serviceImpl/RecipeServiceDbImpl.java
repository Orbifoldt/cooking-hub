package com.cookinghub.recipes.service.serviceImpl;

import com.cookinghub.recipes.model.datasource.RecipeDataSource;
import com.cookinghub.recipes.model.recipes.Recipe;
import com.cookinghub.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceDbImpl implements RecipeService {

    @Autowired
    RecipeDataSource recipeDataSource;

    @Autowired
    StoreStubInfo ding;

    @Override
    public String getMessage() {
        return "Hej världen";
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipeDataSource.getRecipe(id);
    }

    @Override
    public long createRecipe() {
        return 0;
    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }

    @Override
    public Recipe readRecipe(long id) {
        return recipeDataSource.getRecipe(id);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {

    }

    @Override
    public String storeStubRecipes() {
        long id1 = ding.storeStubInfo1();
        long id2 = ding.storeStubInfo2();
        return "Created recipes with ids " + id1 + " and " + id2;
    }


}
