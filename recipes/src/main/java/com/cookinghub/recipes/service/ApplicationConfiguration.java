package com.cookinghub.recipes.service;

import com.cookinghub.recipes.model.datasource.RecipeDataSource;

import com.cookinghub.recipes.model.datasource.databases.ConnectionManager;
import com.cookinghub.recipes.model.datasource.databases.RecipeDataSourceImpl;
import com.cookinghub.recipes.service.serviceImpl.RecipeServiceDbImpl;
import com.cookinghub.recipes.service.serviceImpl.RecipeServiceImplStub;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfiguration {

    @Value("${recipes.datasource.type}")
    private String datasourceType;

    @Value("${recipes.datasource.name}")
    private String datasourceName;

    @Autowired
    RecipeDataSourceImpl databaseRecipeDataSource;

    @Autowired
    RecipeServiceImplStub recipeServiceStub;

    @Autowired
    RecipeServiceDbImpl recipeServiceDb;


    @Bean
    @Primary
    public RecipeDataSource recipeDataSource(){
        if(datasourceType.equals("database")){
            System.out.println("Loading a database recipe data source implementation");
            return databaseRecipeDataSource;
        }
        throw new InvalidPropertyException(RecipeDataSource.class, "recipes.datasource.type", "The value '" + datasourceType + "' is an invalid data source type.");
    }

    @Bean
    @Primary
    @Autowired
    public ConnectionManager connectionManager(ApplicationContext context){
        System.out.println("Loading ConnectionManager with name: " + datasourceName);
        return (ConnectionManager) context.getBean(datasourceName);
    }

    @Bean
    @Primary
    public RecipeService recipeService(){
        if(datasourceType.equals("database")){
            System.out.println("Loading a database recipe service");
            return recipeServiceDb;
        } else {
            System.out.println("Loading a stub recipe service");
            return recipeServiceStub;
        }
    }

}
