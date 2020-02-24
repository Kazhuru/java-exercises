package service.Impl;

import helpers.ApplicationProperties;
import models.Transactions;
import service.TransactionFileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;

public class TransactionFileReaderXMLImpl implements TransactionFileReader {

    @Override
    public Transactions getTransactions(String fileName) {
        Transactions transactions = new Transactions();
        try(FileReader reader =
                    new FileReader(ApplicationProperties.getApplicationFilesPath() + fileName)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Transactions.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            transactions = (Transactions) unmarshaller.unmarshal(reader);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
