package service;

import models.Process;
import models.Transactions;

import java.util.Map;

public interface TransactionsComparator {
    Map<String, Transactions> getTransactionsDifferences(Process process);
}
