package com.cookinghub.recipes.model.datasource.databases.postgresql;

import com.cookinghub.recipes.model.datasource.databases.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("postgresql")
@Profile("!dev")
public class ConnectionManagerPostgres implements ConnectionManager {

    @Value("${recipes.datasource.postgres.host}")
    private String host;

    @Value("${recipes.datasource.postgres.port}")
    private String port;

    @Value("${recipes.datasource.postgres.database}")
    private String databaseName;

    @Value("${recipes.datasource.postgres.user}")
    private String user;

    @Value("${recipes.datasource.postgres.password}")
    private String password;

    private static final String CONNECTION_URL = "jdbc:postgresql://%s:%s/%s?user=%s&password=%s";


    public Connection getConnection() throws SQLException {
        String connectionUrl = String.format(CONNECTION_URL, host, port, databaseName, user, password);
        Connection conn = DriverManager.getConnection(connectionUrl);
        return conn;
    }
}
