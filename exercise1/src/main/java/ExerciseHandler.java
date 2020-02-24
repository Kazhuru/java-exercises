import models.MismatchedTransactions;
import models.TransactionProcess;
import models.Transactions;
import service.DisplayData;
import service.Impl.*;
import service.TransactionFileReader;
import service.TransactionRepository;
import service.TransactionsComparator;

import java.util.Map;

public class ExerciseHandler {
    private Map<String, TransactionFileReader> fileReaderMap;

    public ExerciseHandler(Map<String, TransactionFileReader> fileReaderMap) {
        this.fileReaderMap = fileReaderMap;
    }

    public void executeProcess(String[] inputFiles, String targetFile, String user) {
        TransactionProcess process = new ProcessRepositoryMySQLImpl().saveProcess(user);

        loadAllInputFiles(process, new TransactionInputRepositoryMySQLImpl(), inputFiles);

        loadTargetFile(process, new TransactionTargetRepositoryMySQLImpl(), targetFile);

        MismatchedTransactions mismatchedTransactions =
                getMismatchedTransactions(process, new TransactionsComparatorMySQLImpl());

        printResult(mismatchedTransactions, new DisplayDataHTMLImpl());
    }

    private void loadAllInputFiles(TransactionProcess process,
                                   TransactionRepository repository,
                                   String[] inputFiles) {
        for (String file : inputFiles) {
            String[] fileParts = file.split("\\.");
            String fileType = fileParts[fileParts.length - 1];

            if (fileReaderMap.containsKey(fileType)) {
                TransactionFileReader transactionFileReader = fileReaderMap.get(fileType);
                Transactions transactions = transactionFileReader.getTransactions(file);
                repository.saveTransactions(transactions, process);
            } else {
                System.out.println("Target file not defined!");
            }
        }
    }

    private void loadTargetFile(TransactionProcess process,
                                TransactionRepository repository,
                                String targetFile) {
        String[] fileParts = targetFile.split("\\.");
        String fileType = fileParts[fileParts.length - 1];

        if (fileReaderMap.containsKey(fileType)) {
            TransactionFileReader transactionFileReader = fileReaderMap.get(fileType);
            Transactions transactions = transactionFileReader.getTransactions(targetFile);
            repository.saveTransactions(transactions, process);
        } else {
            System.out.println("Target file not defined!");
        }
    }

    private MismatchedTransactions getMismatchedTransactions(TransactionProcess process,
                                                             TransactionsComparator comparator) {
        return comparator.getMismatchedTransactions(process);
    }

    private void printResult(MismatchedTransactions mismatchedTransactions,
                             DisplayData displayData) {
        displayData.displayTransactionsReport(mismatchedTransactions);
    }
}
