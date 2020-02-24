package helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
    private static final String APPLICATION_FILES_PATH = "exercise1/exercise1-files/";
    private static final String APPLICATION_PROPERTIES_PATH = "exercise1/src/main/resources/application.properties";
    public static Properties properties = new Properties();

    public static Properties getProperties() {
        try (FileReader file = new FileReader(APPLICATION_PROPERTIES_PATH)) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static String getApplicationFilesPath() {
        return APPLICATION_FILES_PATH;
    }

    public static String getApplicationPropertiesPath() {
        return APPLICATION_PROPERTIES_PATH;
    }
}
