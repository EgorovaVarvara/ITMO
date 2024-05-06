package threadUtils;

import connectionUtils.Response;
import utils.ServerLogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;

public class Sender extends Thread {
    private Response response;
    private InetAddress address;
    private int port;
    private DatagramSocket socket;
    private boolean hasNextResponse = false;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    public Sender(DatagramSocket socket){
        this.socket = socket;
    }
    public void putResponse(Response response, DatagramPacket packet){
        this.response = response;
        this.address = packet.getAddress();
        this.port = packet.getPort();
        this.hasNextResponse = true;
        this.start();
    }
    @Override
    public void run(){
        while (!isInterrupted()){
            if (hasNextResponse){
                send(response, address, port);
            }
        }
    }
    public void send(Response response, InetAddress address, int port) {
        byte[] output;
        lock.writeLock().lock();
        try {
            hasNextResponse = false;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(response);
            output = bos.toByteArray();
            ServerLogger.getLogger().log(Level.INFO, "Отправляется результат на %s".formatted(address));
            oos.close();
        } catch (IOException e){
            output = "Не получилось десериализовать ответ".getBytes();
        }
        try {
            DatagramPacket packet = new DatagramPacket(output, output.length, address, port);
            socket.send(packet);
        } catch (IOException e){
            ServerLogger.getLogger().warning("Ошибка при отправке ответа");
        } finally {
            lock.writeLock().unlock();
            this.interrupt();
        }
    }

}
