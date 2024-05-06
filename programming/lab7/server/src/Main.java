import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Server;
import utils.DataBaseParser;


import java.util.HashSet;


public class Main {
    private final static Integer serverPort = 2712;


    public static void main(String[] args) {
        DataBaseParser dataBaseParser = new DataBaseParser();
        HashSet<MusicBand> collection = dataBaseParser.load();
        try {
            CollectionManager collectionManager = new CollectionManager();
            collectionManager.setCollection(collection);
            Server server = new Server(serverPort);
            server.run();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}