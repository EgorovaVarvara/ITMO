package threadUtils;

import connectionUtils.Request;
import connectionUtils.Server;
import utils.ServerLogger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReceiverTask implements Runnable {
    private DatagramSocket socket;
    private Interpreter interpreter;

    public ReceiverTask(DatagramSocket socket, Interpreter interpreter) {
        this.socket = socket;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                byte[] bytes = new byte[Server.bufferSize];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Request request = (Request) objectInputStream.readObject();
                ServerLogger.getLogger().info("Получено сообщение от " + packet.getAddress() + " : " + packet.getPort() + " - " + request.getCommand().getCommandName());
                interpreter.putRequest(request, packet);
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                ServerLogger.getLogger().warning("Невозможно принять сообщение");
            }
        }
    }

}
