package com.cookinghub.recipes.service.serviceImpl;

import com.cookinghub.recipes.model.datasource.RecipeDataSource;
import com.cookinghub.recipes.model.datasource.databases.RecipeDataSourceImpl;
import com.cookinghub.recipes.model.recipes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreStubInfo {

    @Autowired
    RecipeDataSource dataSource;

    public long storeStubInfo1() {
        Recipe recipe = dataSource.storeNewRecipe();
        long id = recipe.getId();
        Recipe recipe1 = new RecipeServiceImplStub().getSoupRecipe(id);
        dataSource.updateRecipe(recipe1);
        return id;
    }

    public long storeStubInfo2(){
        Recipe recipe = dataSource.storeNewRecipe();
        long id = recipe.getId();
        Recipe recipe2 = new RecipeServiceImplStub().getCakeRecipe(id);
        dataSource.updateRecipe(recipe2);
        return id;
    }
}
