package by.patrusova.project.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

class PropertyLoader {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CONNECTION_DB = "src/resources/connectionDB.properties";

    private PropertyLoader() {
    }

    static Properties loadProperties() {
        Properties property = new Properties();
        try (FileInputStream inputStream = new FileInputStream(CONNECTION_DB)) {
            property.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Cannot find database driver's configuration file.");
            throw new RuntimeException(e);
        }
        return property;
    }
}