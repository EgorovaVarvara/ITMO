package utils;

import commands.Command;
import baseClasses.CommandType;
import connectionUtils.*;

import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private final Request request;

    public Client(InetAddress address, int port) {
        try {
            request = new Request(address, port);
            request.setBufferSize(8192 * 8192);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String[] userInput;
        while (true) {
            System.out.println("Введите команду: ");
            userInput = scanner.nextLine().split(" ");
            if (userInput.length < 1) {
                System.out.println("Введите команду: ");
                continue;
            }
            CommandType commandType = CommandUtils.getCommandType(userInput[0]);
            Command command = CommandFactory.createCommand(commandType, userInput);
            if (command == null) continue;
            try {
                request.send(CommandSerializer.serialize(command));
                String response = request.receive();
                if (!response.isEmpty()) System.out.println(response);
            } catch (Exception e) {
                System.err.println("Невозможно отправить запрос/получить ответ сервера: " + e.getMessage());
            }
        }
    }
}
