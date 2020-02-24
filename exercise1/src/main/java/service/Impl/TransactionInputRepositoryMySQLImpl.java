package service.Impl;

import models.Transaction;
import models.TransactionProcess;
import models.Transactions;
import service.TransactionRepository;
import service.TransactionRepositoryMySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionInputRepositoryMySQLImpl
        extends TransactionRepositoryMySQL
        implements TransactionRepository {

    @Override
    public void saveTransactions(Transactions transactions, TransactionProcess process) {
        try (Connection connection = super.openConnection()) {
            String storedProcedure = "{CALL insert_input_transaction(?,?,?,?,?,?)}";
            CallableStatement callableStatement;
            for (Transaction transaction : transactions.getTransactions()) {
                callableStatement = connection.prepareCall(storedProcedure);
                callableStatement.setLong("in_account", transaction.getAccount());
                callableStatement.setDouble("in_amount", transaction.getAmount());
                callableStatement.setString("in_reference", transaction.getReference());
                callableStatement.setString("in_cardname", transaction.getCardName());
                callableStatement.setString("in_cardtype", transaction.getCardType());
                callableStatement.setInt("in_processid", process.getId());

                callableStatement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
