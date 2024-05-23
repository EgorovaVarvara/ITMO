package connectionUtils;


import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


/**
 * Class {@code Sender} sends request to the server
 * @author Egorova Varvara
 */
public class Sender{
    /**
     * Datagram socket that sends request
     */
    private static DatagramSocket socket;
    /**
     * Server address
     */
    private final InetAddress address;
    /**
     * Server port
     */
    private final int port;
    /**
     * Socket timeout
     */
    private final static int socketTimeout = 4000;

    /**
     * Constructor of class with given server address and server port
     * @param address server address
     * @param port server port
     * @throws SocketException if there is error of creating or accessing the socket
     */
    public Sender(InetAddress address, int port) throws SocketException {
        socket = new DatagramSocket();
        socket.setSoTimeout(socketTimeout);
        this.address = address;
        this.port = port;
    }

    /**
     * Getter of server port
     * @return server port
     */
    public int getPort(){
        return socket.getLocalPort();
    }

    /**
     * Setter for buffer size of socket
     * @param size size of buffer
     * @throws SocketException if there is error of creating or accessing the socket
     */
    public void setBufferSize(int size) throws SocketException {
        socket.setReceiveBufferSize(size);
        socket.setSendBufferSize(size);
    }

    /**
     * Method for sending request to server
     * @param bytes request
     * @throws IOException if there is an error of sending
     */
    public void send(byte[] bytes) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, this.address, port);
        socket.send(datagramPacket);
        System.out.print("");
    }

    /**
     * Method for receiving response from server
     *
     * @return String response
     * @throws IOException if there is an error of receiving data
     */
    public Response receive() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024 * 1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
        return (Response)objectInputStream.readObject();
    }
}
