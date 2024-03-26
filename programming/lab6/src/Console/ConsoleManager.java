package Console;

import java.io.File;
import java.util.Scanner;

import FileManager.*;

import Collection.CollectionManager;

/**
 * The {@code ConsoleManager} class manage reading from console.
 *
 * @author Egorova Varvara
 */
public class ConsoleManager implements ReaderWriter{
    /**
     * Constructor that creates object of class {@code ConsoleManager}.
     */
    public ConsoleManager(){
    }

    /**
     * @return Int value from console
     */
    @Override
    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    /**
     * @return Long value from console
     */
    @Override
    public long readLong() {
        Scanner scanner = new Scanner(System.in);
        return Long.parseLong(scanner.nextLine().trim());
    }

    /**
     * @return Float value from console
     */
    @Override
    public float readFloat() {
        Scanner scanner = new Scanner(System.in);
        return Float.parseFloat(scanner.nextLine().trim());
    }

    /**
     * @return Line from console
     */
    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    /**
     * @param text that writes at console
     */
    @Override
    public void write(String text) {
        System.out.print(text);
    }

    /**
     * @param message that writes at console
     * @return validated message from console
     */
    @Override
    public String getValidatedValue(String message) {
        write(message);
        while (true) {
            String userPrint = readLine();
            if (!userPrint.isEmpty() && !userPrint.isBlank()) {
                return userPrint;
            }
        }
    }

    /**
     * Reads filename another time if command started without command line argument.
     */
    public void fileRead(){
        while (true) {
            try {
                System.out.println("Введите название файла еще раз");
                Scanner scanner = new Scanner(System.in);
                String Path = scanner.nextLine();
                CollectionManager collectionManager = new CollectionManager();
                Parser parser = new Parser(Path);
                collectionManager.setFilename(Path);
                collectionManager.setCollection(parser.loadFromJson());
                CommandManager commandManager = new CommandManager(collectionManager);
                commandManager.setFilename(Path);
                while (commandManager.getWork()) {
                    commandManager.existCommand();
                }
            } catch (IllegalArgumentException e){
                System.out.println("Файл не найден");
            }
        }
    }
}
