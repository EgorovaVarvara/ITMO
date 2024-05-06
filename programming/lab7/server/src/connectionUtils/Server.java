
package connectionUtils;

import collection.CollectionManager;
import threadUtils.Interpreter;
import threadUtils.Receiver;
import threadUtils.Sender;
import utils.CommandManager;
import utils.DataBaseParser;
import utils.ServerLogger;

import java.io.*;
import java.io.IOException;
import java.net.*;

import static utils.CommandManager.handlePacket;

public class Server {
    public static final int bufferSize = 1024;
    private final int port;
    private DatagramSocket socket;
    private InetAddress address = InetAddress.getByName("localhost");
    private static CollectionManager collectionManager;

    public Server(int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket(port);
        this.port = port;
    }

    BufferedReader scanner = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));

    public void run() throws InterruptedException {
        DataBaseParser dbParser = new DataBaseParser();
        try {
            Thread consoleThread = new Thread(() -> {
                while (true) {
                    try {
                        if (scanner.ready()) {
                            String line = scanner.readLine();
                            if (line.equals("save") || line.equals("s")) {
                                dbParser.save();
                            }
                            if (line.equals("exit")) {
                                dbParser.save();
                                ServerLogger.getLogger().info("Завершаем работу сервера.");
                                System.exit(0);
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
            });
            ServerLogger.getLogger().info("Сервер запущен на порте " + port);
            CommandManager.setSocket(socket);
            Thread clientThread = new Thread(() -> {
                while (true){
                    try{
                        Sender sender = new Sender(socket);
                        Interpreter interpreter = new Interpreter(sender, socket);
                        Receiver receiver = new Receiver(socket, interpreter);
                        receiver.setDaemon(true);
                        receiver.start();
                    } catch (Exception e){
                        ServerLogger.getLogger().warning("Ошибка: " + e.getMessage());
                    }
                }
            });
            consoleThread.start();
            clientThread.start();
            consoleThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            ServerLogger.getLogger().warning("Ошибка: " + e.getMessage());
        } finally {
            socket.close();
        }
    }
}
