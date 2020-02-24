package service.Impl;

import models.TransactionProcess;
import service.ProcessRepository;
import service.TransactionRepositoryMySQL;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ProcessRepositoryMySQLImpl
        extends TransactionRepositoryMySQL
        implements ProcessRepository {

    @Override
    public TransactionProcess saveProcess(String name) {
        TransactionProcess process = new TransactionProcess();

        try (Connection connection = super.openConnection()) {
            ResultSet resultset;
            CallableStatement callableStatement;
            String storedProcedure = "{CALL insert_process(?)}";

            callableStatement = connection.prepareCall(storedProcedure);
            callableStatement.setString("in_name", name);
            resultset = callableStatement.executeQuery();
            resultset.next();

            process.setId(Integer.parseInt(resultset.getString("lastId")));
            process.setUser(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return process;
    }
}
