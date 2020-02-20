import models.Process;
import models.Transactions;
import service.*;
import service.Impl.*;

import java.util.Map;

public class ExerciseHandler {
    String[] inputFiles;
    String targetFile;
    Process currentProcess;
    FileReader fileReader;
    TransactionRepository transactionInputRepository;
    TransactionRepository transactionTargetRepository;
    TransactionsComparator transactionsComparator;
    ProcessRepository processRepository;
    DisplayData displayData;

    public ExerciseHandler(String[] inputFiles, String targetFile, String user) {
        this.inputFiles = inputFiles;
        this.targetFile = targetFile;
        this.processRepository = new ProcessRepositoryMySQLImpl();
        this.currentProcess = processRepository.saveProcess(user);
        this.transactionInputRepository = new TransactionInputRepositoryMySQLImpl();
        this.transactionTargetRepository = new TransactionTargetRepositoryMySQLImpl();
        this.transactionsComparator = new TransactionsComparatorMySQLImpl();
        this.displayData = new DisplayDataHTMLImpl();
    }

    public void executeProcess() {
        loadAllInputFiles();
        loadTargetFile();
        printResult(compareTransactions());
    }

    private void loadAllInputFiles() {
        for (String file : inputFiles) {
            String[] fileParts = file.split("\\.");
            String fileType = fileParts[fileParts.length - 1];
            String path = "exercise1/exercise1-files/".concat(file);
            switch (fileType) {
                case "csv":
                    fileReader = new FileReaderCSVImpl();
                    transactionInputRepository.saveTransactions(fileReader.getTransactions(path), currentProcess);
                    break;
                case "json":
                    fileReader = new FileReaderJSONImpl();
                    transactionInputRepository.saveTransactions(fileReader.getTransactions(path), currentProcess);
                    break;
                case "xml":
                    fileReader = new FileReaderXMLImpl();
                    transactionInputRepository.saveTransactions(fileReader.getTransactions(path), currentProcess);
                    break;
                case "yml":
                case "yaml":
                    fileReader = new FileReaderYMLImpl();
                    transactionInputRepository.saveTransactions(fileReader.getTransactions(path), currentProcess);
                    break;
                default:
                    System.out.println("unsupported operation for: ".concat(fileType).concat(" files."));
            }
        }
    }

    private void loadTargetFile() {
        fileReader = new FileReaderOUTImpl();
        String path = "exercise1/exercise1-files/".concat(targetFile);
        transactionTargetRepository.saveTransactions(fileReader.getTransactions(path), currentProcess);
    }

    private Map<String, Transactions> compareTransactions() {
        return transactionsComparator.getTransactionsDifferences(currentProcess);
    }

    private void printResult(Map<String, Transactions> transactionsMap) {
        displayData.displayTransactionsReport(transactionsMap);
    }
}
