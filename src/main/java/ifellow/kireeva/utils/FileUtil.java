package ifellow.kireeva.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static String readFileFromResources(String fileName) {
        try (InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Файл не найден: " + fileName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения: " + fileName, e);
        }
    }
}
