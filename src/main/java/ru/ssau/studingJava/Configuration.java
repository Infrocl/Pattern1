package ru.ssau.studingJava;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Properties property;

    private Configuration() {
        try {
            property = new Properties();
            property.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Properties getProperties() {
        if (property == null) {
            new Configuration();
        }
        return property;
    }
}
