package connectionUtils;

import commands.Command;
import baseClasses.CommandType;
import console.Console;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class {@code Client} represents client working
 * @author Egorova Varvara
 */
public class Client {
    /**
     * @see Sender
     */
    private final Sender sender;
    public static User user;

    /**
     * Constructor of class with given server address and server port
     * @param address server address
     * @param port server port
     */
    public Client(InetAddress address, int port) {
        try {
            sender = new Sender(address, port);
            sender.setBufferSize(8192 * 8192);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Method that starts client work
     */
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
            if (!userInput[0].equals("execute_script") & !userInput[0].equals("registration")) {
                CommandType commandType = CommandUtils.getCommandType(userInput[0]);
                Command command = CommandFactory.createCommand(commandType, userInput);
                if (command == null) continue;
                try {
                    command.setUser(user);
                    Request request = new Request(command);
                    sender.send(Serializer.serialize(request));
                } catch (IOException e) {
                    System.out.println("Невозможно отправить запрос серверу: " + e.getMessage());
                    continue;
                }
                try {
                    Response response = sender.receive();
                    if (response.getResponse() != null) System.out.println(response.getResponse());
                } catch (IOException | ClassNotFoundException e){
                    System.out.println("Невозможно получить ответ от сервера: " + e.getMessage());
                }
            } else if (userInput.length == 2 & !userInput[0].equals("registration")){
                try {
                    ScriptExecutor se = new ScriptExecutor(new File(userInput[1])).readScript();
                    ArrayList<Command> commands = se.getCommandList();
                    commands.forEach(command -> {
                        try {
                            command.setUser(user);
                            Request request = new Request(command);
                            sender.send(Serializer.serialize(request));
                        } catch (IOException e) {
                            System.out.println("Невозможно отправить запрос серверу: " + e.getMessage());
                        }
                        try {
                            Response response = sender.receive();
                            if (response.getResponse() != null) System.out.println(response.getResponse());
                        } catch (IOException | ClassNotFoundException e){
                            System.out.println("Невозможно получить ответ сервера: " + e.getMessage());
                        }
                    });
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (userInput[0].equals("execute_script")){
                System.out.println("Неверное количество аргументов для команды execute_script");
            } else {
                System.out.println("Неизвестная команда: " + userInput[0]);
            }
        }
    }
    public void enter(){
        while (user == null) {
            CommandType commandType = CommandUtils.getCommandType("registration");
            Command command = CommandFactory.createCommand(commandType, new String[]{"registration"});
            try {
                Request request = new Request(command);
                sender.send(Serializer.serialize(request));
            } catch (IOException e) {
                System.out.println("Невозможно отправить запрос серверу: " + e.getMessage());
            }
            try {
                Response response = sender.receive();
                if (response.getResponse() != null)System.out.println(response.getResponse());
                if (response.getStatus().equals(ResponseStatus.OK)){
                    user = command.getUser();
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Невозможно получить ответ от сервера: " + e.getMessage());
            }
        }
    }
}
