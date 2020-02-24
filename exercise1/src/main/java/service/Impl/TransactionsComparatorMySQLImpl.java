package service.Impl;

import models.MismatchedTransactions;
import models.TransactionProcess;
import models.Transaction;
import models.Transactions;
import service.TransactionRepositoryMySQL;
import service.TransactionsComparator;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TransactionsComparatorMySQLImpl
        extends TransactionRepositoryMySQL
        implements TransactionsComparator {

    @Override
    public MismatchedTransactions getMismatchedTransactions(TransactionProcess process) {
        MismatchedTransactions mismatchedTransactions = new MismatchedTransactions();

        try(Connection connection = super.openConnection()) {
            ResultSet resultSet;
            CallableStatement callableStatement;

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

            mismatchedTransactions.setInputTransactions(new Transactions(inputTransactions));
            mismatchedTransactions.setTargetTransactions(new Transactions(targetTransactions));
            return mismatchedTransactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mismatchedTransactions;
    }
}
