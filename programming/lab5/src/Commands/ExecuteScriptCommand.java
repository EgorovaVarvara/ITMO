package Commands;

import BaseClasses.Coordinates;
import BaseClasses.Label;
import BaseClasses.MusicBand;
import BaseClasses.MusicGenre;
import Collection.CollectionManager;
import Collection.Validator;
import Console.CommandManager;
import Console.FileReaderManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class ExecuteScriptCommand implements Command{
    CollectionManager cm;
    public ExecuteScriptCommand(CollectionManager cm){
        this.cm = cm;
    }
    Stack<String> files = new Stack<>();
    Validator validator = new Validator();
    @Override
    public void execute(String[] args){
        if (args.length == 2){
            CommandManager commandManager = new CommandManager(cm);
            String filename = args[1];
            try{
                File file = new File(filename);
                files.push(filename);
                Scanner scanner = new Scanner(file);
                FileReaderManager fileReaderManager = new FileReaderManager(filename);
                while (scanner.hasNextLine()){
                    String commandAndArg = scanner.nextLine().trim();
                    commandAndArg = commandAndArg.replaceAll("  ", " ");
                    String command;
                    if (commandAndArg.split(" ").length == 2 || commandAndArg.split(" ").length == 1){
                        command = commandAndArg.split(" ")[0];
                    }else{
                        continue;
                    }
                    if (commandManager.getCommands().containsKey(command)){
                        if (command.equals("add") || command.equals("add_if_min") || command.equals("update") || command.equals("add_if_max") || command.equals("remove_lower")){
                            String name = fileReaderManager.readName();
                            Long x = fileReaderManager.readCoordinateX();
                            float y = fileReaderManager.readCoordinateY();
                            int numberOfParticipants = fileReaderManager.readNumberOfParticipants();
                            MusicGenre musicGenre = fileReaderManager.readMusicGenre();
                            long bands = fileReaderManager.readBands();
                            MusicBand newMusicBand = validator.getValidatedElement(new MusicBand(name, new Coordinates(x, y), numberOfParticipants, musicGenre, new Label(bands)));
                            if (newMusicBand != null){
                                switch (command){
                                    case "add":
                                        cm.add(newMusicBand);
                                    case "add_if_min":
                                        cm.addIfMin(newMusicBand);
                                    case "update":
                                        cm.updateId(newMusicBand, Integer.parseInt(commandAndArg.split(" ")[1]));
                                    case "add_if_max":
                                        cm.addIfMax(newMusicBand);
                                    case "remove_lower":
                                        cm.removeLower(newMusicBand);
                                }
                            }
                        }else if (command.equals("execute_script")){
                            if (!files.contains(commandAndArg.split(" ")[1])){
                                commandManager.getCommands().get(command).execute(commandAndArg.split(" "));
                            }
                        }else{
                            commandManager.getCommands().get(command).execute(commandAndArg.split(" "));
                        }
                    }
                }
                files.pop();
            }catch (FileNotFoundException e){
                System.out.println("Файл не найден. ");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    @Override
    public String getDescription() {
        return "execute_script file_name: считать и исполнить скрипт из указанного файла";
    }
}
