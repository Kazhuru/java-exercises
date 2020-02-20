package service.Impl;

import models.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileReaderJSONImpl implements service.FileReader {

    @Override
    public List<Transaction> getTransactions(String fileName) {
        List<Transaction> transactions =  new LinkedList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(fileName);
            Object parseObject = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) parseObject;

            for (Object jsonObject : jsonArray) {
                transactions.add(createTransaction((JSONObject) jsonObject));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    private Transaction createTransaction(JSONObject jsonObject) {
        return new Transaction(
                Long.parseLong((String) jsonObject.get("account")),
                (Double) jsonObject.get("amount"),
                (String) jsonObject.get("reference"),
                (String) jsonObject.get("card-name"),
                (String) jsonObject.get("card-type")
        );
    }
}
