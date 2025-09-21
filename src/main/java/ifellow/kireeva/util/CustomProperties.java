package ifellow.kireeva.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomProperties {

    private CustomProperties() {
        loadProperties();
    }

    private static class Holder {
        static final CustomProperties INSTANCE = new CustomProperties();
    }

    public static CustomProperties getInstance() {
        return Holder.INSTANCE;
    }

    private Properties props;

    private void loadProperties() {
        props = new Properties();
        try (InputStream input = CustomProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не найден файл config.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки config.properties", e);
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }


}