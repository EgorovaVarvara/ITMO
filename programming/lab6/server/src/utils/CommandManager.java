package utils;

import commands.Command;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;


public class CommandManager {
    private static DatagramChannel channel;

    public static void setChannel(DatagramChannel channel) {
        CommandManager.channel = channel;
    }

    public static void handlePacket(InetSocketAddress sender, byte[] bytes) throws Exception {
        ObjectInputStream objectInputStream2 = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Command command = (Command) objectInputStream2.readObject();
        ServerLogger.getLogger().log(Level.INFO, "Получена команда %s от %s".formatted(command.getCommandName().toUpperCase(), sender));
        byte[] output = command.run().getResponse().getBytes();
        ServerLogger.getLogger().log(Level.INFO, "Отправляется результат на %s".formatted(sender));
        channel.send(ByteBuffer.wrap(output), sender);
    }
}
