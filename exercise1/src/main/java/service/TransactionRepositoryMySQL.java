package service;

import helpers.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class TransactionRepositoryMySQL {

    protected Connection openConnection() {
        Connection connection = null;
        try {
            Properties properties = ApplicationProperties.getProperties();
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
