import utils.Server;

import java.io.IOException;

public class Main {
    public static String collectionFileName;
    private static Integer serverPort;

    static {
        Config config = new Config("server.scfg");
        collectionFileName = config.get("collection_file");
        if (collectionFileName == null) {
            collectionFileName = "server.xml";
        }
        try {
            serverPort = Integer.parseInt(config.get("server_port"));
        } catch (NumberFormatException e) {
            serverPort = 5050;
        }
    }

    public static void main(String[] args) {
        MovieCollection collection;
        try {
            collection = new Xml(new File(collectionFileName)).newReader().parse();
        } catch (IOException e) {
            System.err.println("Unable to find collection file " + collectionFileName);
            System.err.println("New collection file will be created automatically after a few changes.");
            collection = new MovieCollection();
        }
        Server server = new Server(serverPort);
        server.run();
    }
}