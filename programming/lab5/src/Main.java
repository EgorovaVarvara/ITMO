
import Collection.CollectionManager;
import Console.CommandManager;
import Console.ConsoleManager;
import FileManager.FilesStack;
import FileManager.Parser;

import java.io.File;

// ToDo hardcode path to file +
// ToDo not unique id after add +
// ToDo update command not found +
// ToDo validation data in file +
// ToDo execute_script... miss you + (уничтожен)
public class Main {

    // private static final CollectionManager collectionManager = new CollectionManager();
    // private static final ConsoleManager consoleManager = new ConsoleManager();
    // private static Parser parser;
    // private static File file;
    // private static final CommandManager commandManager = new CommandManager(collectionManager);
    public static void main(String[] args) {
        try{ // TODO только мешает отловить ошибку, которая может возникнуть
            CollectionManager collectionManager = new CollectionManager(); // 
            ConsoleManager consoleManager = new ConsoleManager(); //
            while (true){ // TODO как насчет 1) прочитать файл -> он существует? он валидный? можно прочитать? работаем : просим ввести новый файл / работаем с файлом по умолчанию. Уже вне этого цикла запускаем цикл обработки команд
                try{
                    if (args.length > 0){
                        String link = args[0]; //
                        File file = new File(link); // TODO file = new File(args[0])
                        
                        if (file.exists() && !file.isDirectory()){
                            Parser parser = new Parser(link); //
                            collectionManager.setCollection(parser.loadFromJson());
                            CommandManager commandManager = new CommandManager(collectionManager); //
                            commandManager.setFilename(link);
                            collectionManager.setFilename(link);
                            while (commandManager.getWork()){
                                commandManager.existCommand();
                            }
                        }else{
                            consoleManager.fileRead();
                        }
                    }else{
                        consoleManager.fileRead();
                    }
                }catch (IllegalArgumentException e){
                    consoleManager.fileRead();
                }
            }
        }catch (Exception e){
            System.out.println("Something goes wrong. See you next time!");
        }
    }
}
