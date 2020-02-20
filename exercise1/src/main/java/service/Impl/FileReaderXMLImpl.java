package service.Impl;

import models.Transaction;
import models.Transactions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class FileReaderXMLImpl implements service.FileReader {

    @Override
    public List<Transaction> getTransactions(String fileName) {
        Transactions transactions = new Transactions();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Transactions.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            transactions = (Transactions) unmarshaller.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return transactions.getTransactions();
    }

}
