
package connectionUtils;

import collection.CollectionManager;
import threadUtils.Interpreter;
import threadUtils.Receiver;
import threadUtils.Sender;
import utils.DataBaseParser;
import utils.ServerLogger;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class Server {
    public static final int bufferSize = 1024 * 1024;
    private final int port;
    private DatagramSocket socket;
    private static CollectionManager collectionManager;
    private DataBaseParser dbParser;

    public Server(int port, DataBaseParser parser) throws SocketException {
        this.socket = new DatagramSocket(port);
        this.port = port;
        this.dbParser = parser;
    }

    public void run() throws InterruptedException {
        try {
            ServerLogger.getLogger().info("Сервер запущен на порте " + port);
            try {
                Scanner scanner = new Scanner(System.in);
                Sender sender = new Sender(socket);
                sender.start();
                Interpreter interpreter = new Interpreter(sender, socket);
                interpreter.start();
                Receiver receiver = new Receiver(socket, interpreter);
                receiver.setDaemon(true);
                receiver.start();
                while (true) {
                    interpreter.askCommand(scanner, dbParser);
                }
            } catch (Exception e) {
                ServerLogger.getLogger().warning("Ошибка: " + e.getMessage());
            }
        } finally {
            socket.close();
        }
    }
}
