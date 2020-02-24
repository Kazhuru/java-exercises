package service;

import models.MismatchedTransactions;
import models.TransactionProcess;

public interface TransactionsComparator {
    MismatchedTransactions getMismatchedTransactions(TransactionProcess transactionProcess);
}
