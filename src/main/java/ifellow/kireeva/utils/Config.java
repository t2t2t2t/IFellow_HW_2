package ifellow.kireeva.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не найден файл config.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки config.properties", e);
        }
    }


    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

}
