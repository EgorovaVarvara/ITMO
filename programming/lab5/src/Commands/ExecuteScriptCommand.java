package Commands;

import BaseClasses.Coordinates;
import BaseClasses.Label;
import BaseClasses.MusicBand;
import BaseClasses.MusicGenre;
import Collection.CollectionManager;
import Collection.Validator;
import Console.CommandManager;
import Console.FileReaderManager;
import FileManager.FilesStack;

import java.io.FileNotFoundException;
import java.util.HashMap;
import static java.lang.Integer.parseInt;

public class ExecuteScriptCommand implements Command{
    private final CollectionManager cm;
    private final HashMap<String, Command> commands;

    public ExecuteScriptCommand(CollectionManager cm){
        this.cm = cm;
        this.commands = CommandManager.getCommands();
    }
    Validator validator = new Validator();
    @Override
    public void execute(String[] args){
        if (args.length == 2){
            String filename = args[1];
            try {
                FilesStack.addFile(filename);
                FileReaderManager fileReaderManager = new FileReaderManager(filename);
                while (fileReaderManager.getScanner().hasNextLine()) {
                    try {
                        String[] commandAndArg = fileReaderManager.readCommandAndArgument();
                        String command = commandAndArg[0];
                        if (command.equals("add") || command.equals("add_if_max") || command.equals("add_if_min") || command.equals("remove_lower") || command.equals("update")) {
                            String name = fileReaderManager.readName();
                            Long x = fileReaderManager.readCoordinateX();
                            float y = fileReaderManager.readCoordinateY();
                            int numberOfParticipants = fileReaderManager.readNumberOfParticipants();
                            MusicGenre musicGenre = MusicGenre.valueOf(fileReaderManager.readMusicGenre());
                            long bands = fileReaderManager.readBands();
                            MusicBand musicBand = validator.getValidatedElement(new MusicBand(name, new Coordinates(x, y), numberOfParticipants, musicGenre, new Label(bands)));
                            switch (command) {
                                case "add":
                                    cm.add(musicBand);
                                    break;
                                case "add_if_max":
                                    cm.addIfMax(musicBand);
                                    break;
                                case "add_if_min":
                                    cm.addIfMin(musicBand);
                                    break;
                                case "remove_lower":
                                    cm.removeLower(musicBand);
                                    break;
                                case "update":
                                    cm.updateId(musicBand, parseInt(commandAndArg[1]));
                                    break;
                            }
                        } else if (command.equals("execute_script") && (!FilesStack.getFilesStack().contains(commandAndArg[1]))){
                            FilesStack.addFile(commandAndArg[1]);
                            commands.get(command).execute(commandAndArg);
                        } else {
                            commands.get(command).execute(commandAndArg);
                        }
                    } catch (IllegalArgumentException ignored) {
                    }
                }
                FilesStack.removeFile();
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден. ");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    @Override
    public String getDescription() {
        return "execute_script file_name: считать и исполнить скрипт из указанного файла";
    }
}
