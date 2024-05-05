package utils;

import commands.Command;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;


public class CommandManager {
    private static DatagramSocket socket;
    static DataBaseManager dbManager = new DataBaseManager();

    public static void setSocket(DatagramSocket channel) {
        CommandManager.socket = channel;
    }

    public static void handlePacket(InetSocketAddress sender, byte[] bytes) throws Exception {
        ObjectInputStream objectInputStream2 = new ObjectInputStream(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        var request = objectInputStream2.readObject();
        if (request instanceof Command) {
            ServerLogger.getLogger().log(Level.INFO, "Получена команда %s от %s".formatted(((Command) request).getCommandName().toUpperCase(), sender));
            oos.writeObject(((Command) request).run());
        } else if (request instanceof User) {
            ServerLogger.getLogger().info("Получена информация о пользователе от %s".formatted(sender));
            boolean exists = ((User) request).isExists();
            oos.writeObject(exists ? dbManager.authorisation((User) request) : dbManager.registration((User) request));
        } else {
            Response response = new Response(ResponseStatus.ERROR, "ПИЗДА");
            oos.writeObject(response);
        }
        byte[] output = bos.toByteArray();
        ServerLogger.getLogger().log(Level.INFO, "Отправляется результат на %s".formatted(sender));
        DatagramPacket packet = new DatagramPacket(output, output.length, sender.getAddress(), sender.getPort());
        socket.send(packet);
        oos.close();
    }
}

