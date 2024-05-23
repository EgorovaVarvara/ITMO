package threadUtils;

import commands.Command;
import connectionUtils.Request;
import connectionUtils.Response;
import utils.DataBaseParser;
import utils.ServerLogger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;

public class Interpreter extends Thread {
    private Request request;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private Sender sender;
    private boolean hasNextPacket = false;

    public Interpreter(Sender sender, DatagramSocket socket) {
        this.sender = sender;
        this.socket = socket;
    }

    public void putRequest(Request request, DatagramPacket packet) {
        this.request = request;
        this.packet = packet;
        hasNextPacket = true;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (hasNextPacket) {
                interpret(request, packet);
            }
        }
    }

    public void interpret(Request request, DatagramPacket packet) {
        hasNextPacket = false;
        Command command = request.getCommand();
        ServerLogger.getLogger().log(Level.INFO, "Получена команда %s от %s".formatted((command).getCommandName().toUpperCase(), packet.getAddress()));
        Response response = command.run();
        sender.putResponse(response, packet);
    }

    public void askCommand(Scanner scanner, DataBaseParser dbParser) {
        try {
            String line = scanner.nextLine();
            if (line.equals("save") || line.equals("s")) {
                dbParser.save();
            }
            if (line.equals("exit")) {
                dbParser.save();
                ServerLogger.getLogger().info("Завершаем работу сервера.");
                System.exit(0);
            }
        } catch (NoSuchElementException e) {
            dbParser.save();
            ServerLogger.getLogger().info("Экстренно завершаем работу сервера.");
            System.exit(0);
        }
    }
}
