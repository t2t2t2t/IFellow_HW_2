package ifellow.kireeva.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static Integer countTask(String text){

        Pattern pattern = Pattern.compile("\\d+ из (\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new RuntimeException("Не удалось распарсить счётчик: " + text);

    }


}
