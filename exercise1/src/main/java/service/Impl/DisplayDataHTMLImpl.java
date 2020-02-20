package service.Impl;

import models.Transaction;
import models.Transactions;
import service.DisplayData;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


public class DisplayDataHTMLImpl implements DisplayData {

    @Override
    public void displayTransactionsReport(Map<String, Transactions> transactionsMap) {

        try {
            File file = new File("exercise1/exercise1-files/output.html");
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
            for (Map.Entry<String, Transactions> transactionsEntry : transactionsMap.entrySet()) {
                stringBuilder.append("<table style=\"width:100%\">\n")
                        .append("<caption>Different ")
                        .append(transactionsEntry.getKey())
                        .append(" Transactions</caption> <tr>\n")
                        .append("    <th>Account</th>\n")
                        .append("    <th>Amount</th>\n")
                        .append("    <th>Reference</th>\n")
                        .append("    <th>Card Name</th>\n")
                        .append("    <th>Card Type</th>\n")
                        .append("</tr>\n");
                for (Transaction transaction : transactionsEntry.getValue().getTransactions()) {
                    stringBuilder.append("<tr>\n")
                            .append("    <td>").append(transaction.getAccount()).append("</td>\n")
                            .append("    <td>").append(transaction.getAmount()).append("</td>\n")
                            .append("    <td>").append(transaction.getReference()).append("</td>\n")
                            .append("    <td>").append(transaction.getCardName()).append("</td>\n")
                            .append("    <td>").append(transaction.getCardType()).append("</td>\n")
                            .append("</tr>\n");
                }
                stringBuilder.append("</table>\n");
            }
            stringBuilder.append("</body>\n</html>");

            PrintWriter writer = new PrintWriter(file);
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
