package Console;

import java.io.File;
import java.util.Scanner;

import FileManager.*;

import Collection.CollectionManager;

public class ConsoleManager implements ReaderWriter{
    public ConsoleManager(){
    }
    @Override
    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    @Override
    public long readLong() {
        Scanner scanner = new Scanner(System.in);
        return Long.parseLong(scanner.nextLine().trim());
    }

    @Override
    public float readFloat() {
        Scanner scanner = new Scanner(System.in);
        return Float.parseFloat(scanner.nextLine().trim());
    }

    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }

    @Override
    public void write(String text) {
        System.out.print(text);
    }

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

    @Override
    public File readFileName() {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        return new File(String.valueOf(path));
    }
    public void fileRead(){
        while (true) {
            try {
                System.out.println("Введите название файла еще раз");
                Scanner scanner = new Scanner(System.in);
                String Path = scanner.nextLine();
                CollectionManager collectionManager = new CollectionManager();
                Parser parser = new Parser();
                collectionManager.setFilename(Path);
                collectionManager.setCollection(parser.loadFromJson(Path));
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
