package by.patrusova.project.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class PropertyLoader {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CONNECTION_DB = "src/resources/connectionDB.properties";

    private PropertyLoader() {
    }

    static Properties loadProperties() throws IOException {
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream(CONNECTION_DB)) {
            property.load(fis);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, "File does not exist: " + CONNECTION_DB);
            throw new RuntimeException(e);
        }
        return property;
    }
}