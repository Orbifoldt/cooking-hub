package com.cookinghub.recipes.model.datasource.databases.inmemory;

import com.cookinghub.recipes.model.datasource.databases.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("inmemory")
//@Profile("dev")
public class ConnectionManagerInMemory implements ConnectionManager {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    private static final String CONNECTION_URL = "%s?user=%s&password=%s";


    public Connection getConnection() throws SQLException {
        String connectionUrl = String.format(CONNECTION_URL, url, user, password);
        Connection conn = DriverManager.getConnection(connectionUrl);
        return conn;
    }
}