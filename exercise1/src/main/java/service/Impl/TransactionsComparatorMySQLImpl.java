package service.Impl;

import models.Process;
import models.Transaction;
import models.Transactions;
import service.TransactionsComparator;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TransactionsComparatorMySQLImpl implements TransactionsComparator {


    private Connection connection;

    public TransactionsComparatorMySQLImpl() {
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
    public Map<String, Transactions> getTransactionsDifferences(Process process) {
        Map<String, Transactions> transactionsMap = new LinkedHashMap<>();
        ResultSet resultSet;
        CallableStatement callableStatement;
        try {
            String storedProcedure = "{CALL input_transaction_left_exclusive_join(?)}";
            callableStatement = connection.prepareCall(storedProcedure);
            callableStatement.setInt("in_processId", process.getId());

            resultSet = callableStatement.executeQuery();

            List<Transaction> inputTransactions = new LinkedList<>();
            while (resultSet.next()) {
                inputTransactions.add(new Transaction(
                        Long.parseLong(resultSet.getString("inputAccount")),
                        Double.parseDouble(resultSet.getString("inputAmount")),
                        resultSet.getString("inputReference"),
                        resultSet.getString("inputCardname"),
                        resultSet.getString("inputCardtype")));
            }

            storedProcedure = "{CALL target_transaction_left_exclusive_join(?)}";
            callableStatement = connection.prepareCall(storedProcedure);
            callableStatement.setInt("in_processId", process.getId());

            resultSet = callableStatement.executeQuery();

            List<Transaction> targetTransactions = new LinkedList<>();
            while (resultSet.next()) {
                targetTransactions.add(new Transaction(
                        Long.parseLong(resultSet.getString("targetAccount")),
                        Double.parseDouble(resultSet.getString("targetAmount")),
                        resultSet.getString("targetReference"),
                        resultSet.getString("targetCardname"),
                        resultSet.getString("targetCardtype")));
            }
            transactionsMap.put("Input", new Transactions(inputTransactions));
            transactionsMap.put("Target", new Transactions(targetTransactions));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionsMap;
    }
}
