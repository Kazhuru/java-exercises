package service;

import models.TransactionProcess;
import models.Transactions;

public interface TransactionRepository {

    void saveTransactions(Transactions transactions, TransactionProcess transactionProcess);

}
