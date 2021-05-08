package com.cookinghub.recipes.model.datasource.postgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    @Value("datasource.recipes.host")
    private String host;

    @Value("datasource.recipes.port")
    private String port;

    @Value("datasource.recipes.database")
    private String databaseName;

    @Value("datasource.recipes.user")
    private String user;

    @Value("datasource.recipes.password")
    private String password;

    private static final String CONNECTION_URL = "jdbc:postgresql://%s:%s/%s?user=%s&password=%s";


    Connection getConnection() throws SQLException {
        String connectionUrl = String.format(CONNECTION_URL, host, port, databaseName, user, password);
        Connection conn = DriverManager.getConnection(connectionUrl);
        return conn;

    }
}
