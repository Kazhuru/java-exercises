package service;

import models.Transactions;

import java.util.Map;

public interface DisplayData {
    void displayTransactionsReport(Map<String, Transactions> transactionsMap);
}
