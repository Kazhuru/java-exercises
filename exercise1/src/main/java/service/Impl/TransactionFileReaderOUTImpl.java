package service.Impl;

import builders.TransactionBuilder;
import helpers.ApplicationProperties;
import models.Transaction;
import models.Transactions;
import service.TransactionFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class TransactionFileReaderOUTImpl implements TransactionFileReader {

    @Override
    public Transactions getTransactions(String fileName) {
        List<Transaction> transactionList = new LinkedList<>();
        try (BufferedReader bufferedReader =
                     Files.newBufferedReader(
                             Paths.get(ApplicationProperties.getApplicationFilesPath() + fileName),
                             StandardCharsets.US_ASCII)) {
            bufferedReader.lines().forEach(line -> {
                String[] transactionData = line.split("\\|");
                transactionList.add(createTransaction(transactionData));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Transactions(transactionList);
    }

    private Transaction createTransaction(String[] transactionStringArray) {
        return new TransactionBuilder()
                       .account(Long.parseLong(transactionStringArray[0]))
                       .amount(Double.parseDouble(transactionStringArray[1]))
                       .reference(transactionStringArray[2])
                       .cardName(transactionStringArray[3])
                       .cardType(transactionStringArray[4])
                       .build();
    }
}
