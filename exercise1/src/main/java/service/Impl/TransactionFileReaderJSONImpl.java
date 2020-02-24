package service.Impl;

import builders.TransactionBuilder;
import helpers.ApplicationProperties;
import models.Transaction;
import models.Transactions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.TransactionFileReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TransactionFileReaderJSONImpl implements TransactionFileReader {

    @Override
    public Transactions getTransactions(String fileName) {
        List<Transaction> transactionList = new LinkedList<>();

        try(FileReader reader =
                    new FileReader(ApplicationProperties.getApplicationFilesPath() + fileName)) {
            Object parseObject = new JSONParser().parse(reader);
            JSONArray jsonArray = (JSONArray) parseObject;
            for (Object jsonObject : jsonArray) {
                transactionList.add(createTransaction((JSONObject) jsonObject));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Transactions(transactionList);
    }

    private Transaction createTransaction(JSONObject jsonObject) {
        return new TransactionBuilder()
                       .account(Long.parseLong((String) jsonObject.get("account")))
                       .amount((Double) jsonObject.get("amount"))
                       .reference((String) jsonObject.get("reference"))
                       .cardName((String) jsonObject.get("card-name"))
                       .cardType((String) jsonObject.get("card-type"))
                       .build();
    }
}
