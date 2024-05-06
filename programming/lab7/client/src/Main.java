import connectionUtils.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;

public class Main {
    private static final InetAddress serverAddress;

    static {
        try {
            serverAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int serverPort = 2712;

    public static void main(String[] args) {
        Client client = new Client(serverAddress, serverPort);
        try {
            client.enter();
            System.out.println("Для получения справки по доступным командам введите help");
            client.run();
        } catch (NoSuchElementException e) {
            System.out.println("Ну хорошо же все начиналось...");
        }
    }
}
