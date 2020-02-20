package service.Impl;

import models.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FileReaderOUTImpl implements service.FileReader {

    @Override
    public List<Transaction> getTransactions(String fileName) {
        List<Transaction> transactions = new LinkedList<>();
        Path path = Paths.get(fileName);
        try {
            BufferedReader bufferedReader =
                    Files.newBufferedReader(path, StandardCharsets.US_ASCII);

            String outLine = bufferedReader.readLine();
            while (outLine != null) {
                String[] transactionData = outLine.split("\\|");
                transactions.add(createTransaction(transactionData));
                outLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    private Transaction createTransaction(String[] transactionStringArray) {
        return new Transaction(
                Long.parseLong(transactionStringArray[0]),
                Double.parseDouble(transactionStringArray[1]),
                transactionStringArray[2], transactionStringArray[3], transactionStringArray[4]);
    }
}
