package com.cookinghub.recipes.service.serviceImpl;

import com.cookinghub.recipes.model.datasource.RecipeDataSource;
import com.cookinghub.recipes.model.datasource.postgresql.RecipeDataSourceImpl;
import com.cookinghub.recipes.model.recipes.*;

import java.util.Arrays;
import java.util.List;

public class StoreStubInfo {

    public static void storeStubInfoPostgreSql(){
        RecipeDataSource dataSource = new RecipeDataSourceImpl();

        Recipe recipe = dataSource.storeNewRecipe();
        long id = recipe.getId();
        Recipe recipe1 = new RecipeServiceImplStub().getSoupRecipe(id);
        dataSource.updateRecipe(recipe1);

        recipe = dataSource.storeNewRecipe();
        id = recipe.getId();
        Recipe recipe2 = new RecipeServiceImplStub().getCakeRecipe(id);
        dataSource.updateRecipe(recipe2);
    }
}
