package com.cookinghub.recipes.model.datasource.databases.inmemory;

import com.cookinghub.recipes.model.datasource.databases.ConnectionManager;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component("inmemory")
public class ConnectionManagerInMemory implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return null;
    }
}
