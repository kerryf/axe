package axe;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Axe {

    private static final Logger LOGGER = LoggerFactory.getLogger(Axe.class);

    public static void main(String[] args) {

        try {
            Lifecycle.setUp();

            Javalin app = Javalin.create(config -> {
                config.showJavalinBanner = false;
                config.staticFiles.enableWebjars();
                config.staticFiles.add("public", Location.CLASSPATH);
            });

            Runtime.getRuntime().addShutdownHook(new Thread(app::stop));

            app.events(listener -> {
                listener.serverStopped(Lifecycle::cleanUp);
            });

            String host = Helper.config("host");
            int port = Helper.configAsInt("port");
            app.start(host, port);

            app.get("/", context -> context.result("Hello Axe!"));
        }
        catch (Exception e) {
            LOGGER.error("Error starting Axe", e);
            System.exit(1);
        }
    }
}
