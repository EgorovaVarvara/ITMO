
package connectionUtils;

import collection.CollectionManager;
import utils.CommandManager;
import utils.DataBaseParser;
import utils.ServerLogger;

import java.io.*;
import java.io.IOException;
import java.net.*;

import static utils.CommandManager.handlePacket;

public class Server {
    private static final int bufferSize = 1024;
    private final int port;
    private DatagramSocket socket;
    private InetAddress address = InetAddress.getByName("localhost");
    private static CollectionManager collectionManager;

    public Server(CollectionManager collectionManager, int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket(port);
        Server.collectionManager = collectionManager;
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
                        DatagramPacket packet = new DatagramPacket(new byte[bufferSize], bufferSize);
                        socket.receive(packet);
                        InetSocketAddress senderAddress = new InetSocketAddress(packet.getAddress(), packet.getPort());
                        handlePacket(senderAddress, packet.getData());
                    } catch (Exception e){
                        byte[] message = "ERROR: Что-то пошло не так...".getBytes();
                        try {
                            socket.send(new DatagramPacket(message, message.length, address, port));
                        } catch (IOException ex) {
                            ServerLogger.getLogger().warning("Ошибка: " + e.getMessage());
                        }
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
