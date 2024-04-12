package connectionUtils;

import baseClasses.MusicBand;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 21L;
    private static DatagramSocket socket;
    private final InetAddress address;
    private final int port;
    private final static int socketTimeout = 4000;
    private int chunkSize;
    public Request(InetAddress address, int port) throws SocketException {
        socket = new DatagramSocket();
        socket.setSoTimeout(socketTimeout);
        this.address = address;
        this.port = port;
    }
    public int getPort(){
        return socket.getLocalPort();
    }
    public void setBufferSize(int size) throws SocketException {
        socket.setReceiveBufferSize(size);
        socket.setSendBufferSize(size);
        this.chunkSize = 1024;
    }
    public void send(String message) throws Exception{
        this.send(message.getBytes());
    }
    public void send(byte[] bytes) throws IOException, InterruptedException {
        int numChunks = (int) Math.ceil((double) bytes.length / chunkSize);
        for (int i = 0; i < numChunks; i++) {
            int offset = i * chunkSize;
            int length = Math.min(bytes.length - offset, chunkSize);
            byte[] chunk = new byte[length + 1];
            chunk[length] = (numChunks == 1 || i + 1 == numChunks) ? (byte) 0 : (byte) 1;
            System.arraycopy(bytes, offset, chunk, 0, length);
            DatagramPacket datagramPacket = new DatagramPacket(chunk, length + 1, this.address, port);
            socket.send(datagramPacket);
            if (i != 0 && i % 50 == 0) {
                System.out.println("Sending chunks");
                Thread.sleep(100);
            }
        }
        System.out.print("");
    }
    public String receive() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[chunkSize + 1];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        boolean hasNext = (packet.getData()[packet.getLength() - 1] & 0xFF) == 1;
        bos.write(packet.getData(), 0, packet.getLength() - 1);
        while (hasNext) {
            socket.receive(packet);
            hasNext = (packet.getData()[packet.getLength() - 1] & 0xFF) == 1;
            bos.write(packet.getData(), 0, packet.getLength() - 1);
        }
        return bos.toString();
    }
//    private String commandName;
//    private String args = "";
//    private MusicBand musicBand = null;
//    public Request(ResponseStatus responseStatus, String commandName, MusicBand musicBand){
//        this.commandName = commandName.trim();
//    }
//    public Request(String commandName, String args){
//        this.commandName = commandName.trim();
//        this.args = args.trim();
//    }
//    public Request(String commandName, MusicBand musicBand){
//        this.commandName = commandName.trim();
//        this.musicBand = musicBand;
//    }
//    public Request(String commandName, String args, MusicBand musicBand){
//        this.commandName = commandName.trim();
//        this.args = args.trim();
//        this.musicBand = musicBand;
//    }
//    public boolean isEmpty(){
//        return commandName.isEmpty() && args.isEmpty() && musicBand == null;
//    }
//    public String getCommandName(){
//        return commandName;
//    }
//    public String getArgs() {
//        return args;
//    }
//    public MusicBand getMusicBand() {
//        return musicBand;
//    }
//    @Override
//    public boolean equals(Object o){
//        if (this == o) return true;
//        if (!(o instanceof Request request)) return false;
//        return Objects.equals(commandName, request.commandName) && Objects.equals(args, request.args) && Objects.equals(musicBand, request.musicBand);
//    }
//    @Override
//    public int hashCode(){
//        return Objects.hash(commandName, args, musicBand);
//    }
//    @Override
//    public String toString(){
//     return "Request[" + commandName +
//             (args.isEmpty()
//                ? ""
//                : ", " + args) +
//             ((musicBand == null)
//                ? "]"
//                : ", " + musicBand + "]");
//    }
}
