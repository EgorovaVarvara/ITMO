
import Collection.CollectionManager;
import Console.CommandManager;
import Console.ConsoleManager;
import FileManager.Parser;

import java.io.File;

// ToDo hardcode path to file +
// ToDo not unique id after add +
// ToDo update command not found +
// ToDo validation data in file +
// ToDo execute_script... miss you + (уничтожен)
public class Main {
    public static void main(String[] args) {
        try{
            CollectionManager collectionManager = new CollectionManager();
            ConsoleManager consoleManager = new ConsoleManager();
            while (true){
                try{
                    if (args.length > 0){
                        String link = args[0];
                        File file = new File(link);
                        if (file.exists() && !file.isDirectory()){
                            Parser parser = new Parser(link);
                            collectionManager.setCollection(parser.loadFromJson());
                            CommandManager commandManager = new CommandManager(collectionManager);
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
