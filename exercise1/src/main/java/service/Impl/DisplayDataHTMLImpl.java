package service.Impl;

import helpers.ApplicationProperties;
import models.MismatchedTransactions;
import models.Transaction;
import service.DisplayData;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class DisplayDataHTMLImpl implements DisplayData {

    @Override
    public void displayTransactionsReport(MismatchedTransactions mismatchedTransactions) {


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<html>\n <head>\n <title>Transactions Results</title>\n")
                .append("<style>\n")
                .append("table, th, td {\n")
                .append("  border: 1px solid black;\n")
                .append("  border-collapse: collapse;\n}\n")
                .append("}\n")
                .append("th, td {\n")
                .append("  padding: 5px;\n")
                .append("  text-align: left;\n}\n")
                .append("</style>\n")
                .append("</head>\n")
                .append("<body>\n <h1>Transactions Join Result:</h1>\n");

        stringBuilder.append("<table style=\"width:100%\">\n")
                .append("<caption>Different Input Transactions</caption> <tr>\n")
                .append("    <th>Account</th>\n")
                .append("    <th>Amount</th>\n")
                .append("    <th>Reference</th>\n")
                .append("    <th>Card Name</th>\n")
                .append("    <th>Card Type</th>\n")
                .append("</tr>\n");
        for (Transaction transaction : mismatchedTransactions
                                               .getInputTransactions()
                                               .getTransactions()) {
            stringBuilder.append("<tr>\n")
                    .append("    <td>").append(transaction.getAccount()).append("</td>\n")
                    .append("    <td>").append(transaction.getAmount()).append("</td>\n")
                    .append("    <td>").append(transaction.getReference()).append("</td>\n")
                    .append("    <td>").append(transaction.getCardName()).append("</td>\n")
                    .append("    <td>").append(transaction.getCardType()).append("</td>\n")
                    .append("</tr>\n");
        }
        stringBuilder.append("</table>\n");

        stringBuilder.append("<table style=\"width:100%\">\n")
                .append("<caption>Different Target Transactions</caption> <tr>\n")
                .append("    <th>Account</th>\n")
                .append("    <th>Amount</th>\n")
                .append("    <th>Reference</th>\n")
                .append("    <th>Card Name</th>\n")
                .append("    <th>Card Type</th>\n")
                .append("</tr>\n");
        for (Transaction transaction : mismatchedTransactions
                                               .getTargetTransactions()
                                               .getTransactions()) {
            stringBuilder.append("<tr>\n")
                    .append("    <td>").append(transaction.getAccount()).append("</td>\n")
                    .append("    <td>").append(transaction.getAmount()).append("</td>\n")
                    .append("    <td>").append(transaction.getReference()).append("</td>\n")
                    .append("    <td>").append(transaction.getCardName()).append("</td>\n")
                    .append("    <td>").append(transaction.getCardType()).append("</td>\n")
                    .append("</tr>\n");
        }
        stringBuilder.append("</table>\n");

        stringBuilder.append("</body>\n</html>");

        File file = new File("exercise1/exercise1-files/output.html");
        try (PrintWriter writer =
                     new PrintWriter(ApplicationProperties.getApplicationFilesPath() + "output.html")) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
