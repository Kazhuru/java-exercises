package service;

import models.Transaction;

import java.util.List;

public interface FileReader {

    List<Transaction> getTransactions(String fileName);
}
