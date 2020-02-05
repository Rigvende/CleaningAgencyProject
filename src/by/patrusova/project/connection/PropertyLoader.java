package by.patrusova.project.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class PropertyLoader {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CONNECTION_DB
            = "src" + File.separator + "resources" + File.separator + "connectionDB.properties";

    private PropertyLoader() {
    }

    static Properties loadProperties() {
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream(CONNECTION_DB)) {
            property.load(fis); //fixme томкат не видит ресурсы, а проект видит
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Cannot find database driver's configuration file.");
            throw new RuntimeException(e);
        }
        return property;
    }
}