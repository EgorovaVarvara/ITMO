package utils;

import Commands.Command;
import baseClasses.CommandType;
import console.Console;
import connectionUtils.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    private final Request request;
    public Client(InetAddress address, int port){
        try {
            request = new Request(address, port);
            request.setBufferSize(8192 * 8192);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        CollectionValidator.setRequest(request);
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        String[] userInput;
        while (true){
            System.out.println("Введите команду: ");
            userInput = scanner.nextLine().split(" ");
            if (userInput.length < 1) continue;
            String[] args = Arrays.copyOfRange(userInput, 1, userInput.length);
            CommandType commandType = CommandUtils.getCommandType(userInput[0]);
            Command command = CommandFactory.createCommand(commandType, args);
            if (command == null) continue;
            try {
                request.send(CommandSerializer.serialize(command));
                String response = request.receive();
                if (List.of(CommandType.FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS, CommandType.PRINT_DESCENDING, CommandType.SHOW).contains(commandType)){
                    String[] musicBands = response.split("\\.\n");
                    for (String musicBand : musicBands){
                        System.out.println(musicBand);
                    }
                    continue;
                }
                if (!response.isEmpty()) System.out.println(response);
            } catch (Exception e) {
                System.err.println("Unable to send/receive request/response to/from the server: " + e.getMessage());
            }
        }
    }
//    private String host;
//    private int port;
//    private int reconnectionTimeout;
//    private int reconnectionAttempts;
//    private int maxReconnectionAttempts;
//    private Console console;
//    private Socket socket;
//    private ObjectOutputStream serverWriter;
//    private ObjectInputStream serverReader;
//    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts, Console console) {
//        this.host = host;
//        this.port = port;
//        this.reconnectionTimeout = reconnectionTimeout;
//        this.maxReconnectionAttempts = maxReconnectionAttempts;
//        this.console = console;
//    }
//
//    public Response sendAndAskResponse(Request request){
//        while (true) {
//            try {
//                if(Objects.isNull(serverWriter) || Objects.isNull(serverReader)) throw new IOException();
//                if (request.isEmpty()) return new Response(ResponseStatus.WRONG_ARGUMENTS, "Запрос пустой!");
//                serverWriter.writeObject(request);
//                serverWriter.flush();
//                Response response = (Response) serverReader.readObject();
//                this.disconnectFromServer();
//                reconnectionAttempts = 0;
//                return response;
//            } catch (IOException e) {
//                if (reconnectionAttempts == 0){
//                    connectToServer();
//                    reconnectionAttempts++;
//                    continue;
//                } else {
//                    console.printError("Соединение с сервером разорвано");
//                }
//                try {
//                    reconnectionAttempts++;
//                    if (reconnectionAttempts >= maxReconnectionAttempts) {
//                        console.printError("Превышено максимальное количество попыток соединения с сервером");
//                        return new Response(ResponseStatus.EXIT);
//                    }
//                    console.write("Повторная попытка через " + reconnectionTimeout / 1000 + " секунд");
//                    Thread.sleep(reconnectionTimeout);
//                    connectToServer();
//                } catch (Exception exception) {
//                    console.printError("Попытка соединения с сервером неуспешна");
//                }
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public void connectToServer(){
//        try{
//            if(reconnectionAttempts > 0) console.write("Попытка повторного подключения");
//            this.socket = new Socket(host, port);
//            this.serverWriter = new ObjectOutputStream(socket.getOutputStream());
//            this.serverReader = new ObjectInputStream(socket.getInputStream());
//        } catch (IllegalArgumentException e){
//            console.printError("Адрес сервера введен некорректно");
//        } catch (IOException e) {
//            console.printError("Произошла ошибка при соединении с сервером");
//        }
//    }
//
//    public void disconnectFromServer(){
//        try {
//            this.socket.close();
//            serverWriter.close();
//            serverReader.close();
//        } catch (IOException e) {
//            console.printError("Не подключен к серверу");
//        }
//    }
}
