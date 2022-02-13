package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final Logger log = LoggerFactory.getLogger(PropertyReader.class);
    private static PropertyReader envProperties;

    private Properties loadProperties() {
        File file = new File("./src/test/resources/config.properties");
        FileInputStream fileInput = null;
        Properties props = new Properties();

        try {
            fileInput = new FileInputStream(file);
            props.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException e) {
            log.error("config.properties is missing or corrupt : " + e.getMessage());
        } catch (IOException e) {
            log.error("read failed due to: " + e.getMessage());
        }

        return props;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static PropertyReader getInstance() {
        if (envProperties == null) {
            envProperties = new PropertyReader();
        }
        return envProperties;
    }

    private Properties properties;

    private PropertyReader() {
        properties = loadProperties();
    }
}
