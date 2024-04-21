import baseClasses.MusicBand;
import collection.CollectionManager;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import utils.Parser;
import utils.Server;


import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;

public class Main {
    private final static Integer serverPort = 2712;


    public static void main(String[] args) throws InterruptedException, IOException {
        HashSet<MusicBand> collection;
        try {
            collection = Parser.loadFromJson();
        } catch (JsonIOException | JsonSyntaxException e) {
            System.err.println("Не найден файл с коллекцией: " + Parser.getFilename());
            System.err.println("Создана новая коллекция.");
            collection = new HashSet<>();
        }
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.setCollection(collection);
        Server server = new Server(collectionManager, serverPort);
        server.run();
    }
}