package service;

import models.Transaction;
import models.Transactions;

import java.util.List;

public interface TransactionFileReader {

    Transactions getTransactions(String fileName);
}
