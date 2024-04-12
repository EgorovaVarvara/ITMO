package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ServerLogger {
    private static final Logger logger = Logger.getLogger(ServerLogger.class.getName());
    private static final FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("server_logs.log");
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
