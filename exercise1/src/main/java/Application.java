import service.Impl.*;
import service.TransactionFileReader;

import java.util.HashMap;
import java.util.Map;

public class Application {

    private static Map<String, TransactionFileReader> fileReaderMap;

    static {
        fileReaderMap = new HashMap<>();
        fileReaderMap.put("csv", new TransactionFileReaderCSVImpl());
        fileReaderMap.put("json", new TransactionFileReaderJSONImpl());
        fileReaderMap.put("xml", new TransactionFileReaderXMLImpl());
        fileReaderMap.put("yml", new TransactionFileReaderYMLImpl());
        fileReaderMap.put("out", new TransactionFileReaderOUTImpl());
    }

    public static void main(String[] args) {
        new ExerciseHandler(fileReaderMap)
                .executeProcess(new String[]{
                                "input.csv",
                                "input.json",
                                "input.xml",
                                "input.yml"},
                        "target.out",
                        "CarloSR");
    }
}
