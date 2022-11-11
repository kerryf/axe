package axe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TimeZone;

class Lifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lifecycle.class);

    private Lifecycle() {
        // Hidden constructor
    }

    static void setUp() throws Exception {
        LOGGER.info("Starting setup tasks...");

        Properties config = Helper.loadConfig("Axe.properties");
        LOGGER.info("Loaded configuration");

        setLocale(config);
        LOGGER.info("Set locale: {}", Locale.getDefault().toString());

        setTimeZone(config);
        LOGGER.info("Set time zone: {}", TimeZone.getDefault().getDisplayName());

        ResourceBundle bundle = ResourceBundle.getBundle("i18n/Messages");
        LOGGER.info("Loaded resource bundle");

        Backpack backpack = Backpack.getInstance();
        backpack.add("axe.config", config);
        backpack.add("axe.bundle", bundle);

        LOGGER.info("Axe setup complete");
    }

    private static void setLocale(Properties config) {

        String language = config.getProperty("language");
        String country = config.getProperty("country");

        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);
    }

    private static void setTimeZone(Properties config) {

        String region = config.getProperty("zone");

        ZoneId zoneId = ZoneId.of(region);
        TimeZone zone = TimeZone.getTimeZone(zoneId);
        TimeZone.setDefault(zone);
    }

    static void cleanUp() {
        System.out.println("cleanUp called");
    }
}
