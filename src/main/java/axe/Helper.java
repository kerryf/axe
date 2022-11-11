package axe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

public class Helper {

    private Helper() {
        // Hidden constructor
    }

    public static Properties loadConfig(String name) throws IOException {

        try (InputStream stream = new FileInputStream("conf/" + name)) {

            Properties config = new Properties();
            config.load(stream);

            return config;
        }
    }

    public static Properties getConfig() {
        return (Properties) Backpack.getInstance().get("axe.config");
    }

    public static ResourceBundle getBundle() {
        return (ResourceBundle) Backpack.getInstance().get("axe.bundle");
    }

    public static String config(String key) {
        return getConfig().getProperty(key);
    }

    public static int configAsInt(String key) {

        String value = getConfig().getProperty(key);
        return Integer.parseInt(value);
    }

    public static boolean configAsBoolean(String key) {

        String value = getConfig().getProperty(key);
        return Boolean.parseBoolean(value);
    }

    public static String message(String key) {
        return getBundle().getString(key);
    }

    public static String message(String key, Object... args) {

        String pattern = getBundle().getString(key);
        return MessageFormat.format(pattern, args);
    }
}
