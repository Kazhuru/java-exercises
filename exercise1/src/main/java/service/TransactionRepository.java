package service;

import models.Process;
import models.Transaction;

import java.util.List;

public interface TransactionRepository {

    void saveTransactions(List<Transaction> transactions, Process process);

}
