package service.Impl;

import models.Process;
import models.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class TransactionTargetRepositoryMySQLImpl implements service.TransactionRepository {
    private Connection connection;

    public TransactionTargetRepositoryMySQLImpl() {
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
    public void saveTransactions(List<Transaction> transactions, Process process) {
        String storedProcedure = "{CALL insert_target_transaction(?,?,?,?,?,?)}";
        CallableStatement callableStatement;

        for (Transaction transaction : transactions) {
            try {
                callableStatement = connection.prepareCall(storedProcedure);
                callableStatement.setLong("in_account", transaction.getAccount());
                callableStatement.setDouble("in_amount", transaction.getAmount());
                callableStatement.setString("in_reference", transaction.getReference());
                callableStatement.setString("in_cardname", transaction.getCardName());
                callableStatement.setString("in_cardtype", transaction.getCardType());
                callableStatement.setInt("in_processid", process.getId());

                callableStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
