package service.Impl;

import models.Process;
import service.ProcessRepository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ProcessRepositoryMySQLImpl implements ProcessRepository {
    private Connection connection;

    public ProcessRepositoryMySQLImpl() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("exercise1/src/main/resources/database.properties"));
            Class.forName(properties.getProperty("driver"));
            this.connection = DriverManager.getConnection(
                    properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Process saveProcess(String name) {
        String saveProcessProcedure = "{CALL insert_process(?)}";
        CallableStatement callableStatement;
        ResultSet resultset;
        int id = Integer.MIN_VALUE;

        try {
            callableStatement = connection.prepareCall(saveProcessProcedure);
            callableStatement.setString("in_name", name);
            resultset = callableStatement.executeQuery();
            resultset.next();
            id = Integer.parseInt(resultset.getString("lastId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Process(id, name);
    }
}
