import console.BlankConsole;
import console.Console;
import console.ReaderWriter;
import utils.Client;
import utils.RuntimeManager;

import java.util.Scanner;

public class Main {
    private static String host;
    private static int port;
    private static final ReaderWriter console = new BlankConsole();

    public static boolean parseHostPort(String[] args) {
        try {
            if (args.length != 2) throw new IllegalArgumentException("Передайте хост и порт в аргументы " +
                    "командной строки в формате <host> <port>");
            host = args[0];
            port = Integer.parseInt(args[1]);
            if (port < 0) throw new IllegalArgumentException("Порт должен быть натуральным числом");
            return true;
        } catch (IllegalArgumentException e) {
            console.printError(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Для получения справки о доступных командах введите help");
        if (!parseHostPort(args)) return;
        Console console = new Console();
        Client client = new Client(host, port, 5000, 5, console);
        new RuntimeManager(console, new Scanner(System.in), client).interactiveMode();
    }
}
