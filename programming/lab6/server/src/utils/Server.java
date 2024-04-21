package utils;

import collection.CollectionManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import static utils.CommandManager.handlePacket;
import static utils.CommandManager.setChannel;

public class Server {
    private static final int bufferSize = 1025;
    private final int port;
    private DatagramChannel datagramChannel;
    private Selector selector;
    private static CollectionManager collectionManager;
    public Server(CollectionManager collectionManager, int port){
        Server.collectionManager = collectionManager;
        this.port = port;
    }
    public void run(){
        Thread thread = new Thread();
        thread.start();
        try{
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.socket().bind(new InetSocketAddress(port));
            selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ);
            ServerLogger.getLogger().info("Starting server on port " + port);
            Map<InetSocketAddress , ByteArrayOutputStream> byteStreams = new HashMap<>();
            while (true){
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isReadable()){
                        DatagramChannel keyChannel = (DatagramChannel) key.channel();
                        setChannel(keyChannel);
                        ByteBuffer buffer = ByteBuffer.allocate(1025);
                        InetSocketAddress inetSocketAddress = (InetSocketAddress) keyChannel.receive(buffer);
                        ByteArrayOutputStream byteStream = byteStreams.get(inetSocketAddress);
                        if (byteStream == null) {
                            byteStream = new ByteArrayOutputStream();
                            byteStreams.put(inetSocketAddress, byteStream);
                        }
                        boolean hasNext = buffer.array()[buffer.limit() - 1] == 1;
                        byteStream.write(buffer.array(), 0, buffer.limit() - 1);
                        if (!hasNext) {
                            try {
                                handlePacket(inetSocketAddress, byteStream.toByteArray());
                            } catch (Exception e) {
                                keyChannel.send(ByteBuffer.wrap("ERROR: Something went wrong...".getBytes()), inetSocketAddress);
                                ServerLogger.getLogger().warning(e.toString());
                            }
                            byteStreams.remove(inetSocketAddress);
                        }
                    }
                }
            }
        } catch (IOException e){
            ServerLogger.getLogger().warning("Exception: " + e.getMessage());
        }finally {
            try {
                selector.close();
                datagramChannel.close();
            } catch (IOException e) {
                ServerLogger.getLogger().warning("Exception while closing channel or selector: " + e.getMessage());
            }
        }
    }
}
