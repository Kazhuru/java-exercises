package service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import helpers.ApplicationProperties;
import models.Transactions;
import service.TransactionFileReader;

import java.io.FileReader;
import java.io.IOException;

public class TransactionFileReaderYMLImpl implements TransactionFileReader {

    @Override
    public Transactions getTransactions(String fileName) {
        Transactions transactions = new Transactions();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        //objectMapper.findAndRegisterModules();// Todo of removal
        try(FileReader reader =
                    new FileReader(ApplicationProperties.getApplicationFilesPath() + fileName)) {
            transactions = objectMapper.readValue(reader, Transactions.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
