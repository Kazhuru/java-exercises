package service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import models.Transaction;
import models.Transactions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileReaderYMLImpl implements service.FileReader {

    @Override
    public List<Transaction> getTransactions(String fileName) {
        Transactions transactions = new Transactions();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        try {
            transactions = objectMapper.readValue(new File(fileName), Transactions.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions.getTransactions();
    }
}
