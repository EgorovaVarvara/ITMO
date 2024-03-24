package Commands;

import BaseClasses.Coordinates;
import BaseClasses.Label;
import BaseClasses.MusicBand;
import BaseClasses.MusicGenre;
import Collection.CollectionManager;
import Collection.Validator;
import Console.CommandManager;
import FileManager.FileReaderManager;
import FileManager.FilesStack;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static java.lang.Integer.parseInt;
/**
 * Command `execute_script file_name`.
 *
 * @author Egorova Varvara
 */

public class ExecuteScriptCommand implements Command {
    /**
     * @see CollectionManager
     */
    private final CollectionManager cm;
    /**
     * @see CommandManager
     */
    private final HashMap<String, Command> commands;
    /**
     * Constructor that creates object of {@code ExecuteScriptCommand}.
     * @param cm collection manager
     */
    public ExecuteScriptCommand(CollectionManager cm) {
        this.cm = cm;
        this.commands = CommandManager.getCommands();
    }

    /**
     * @see Validator
     */
    Validator validator = new Validator();
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String filename = args[1];
            if (!FilesStack.getFilesStack().contains(filename)) {
                FilesStack.addFile(filename);
                try {
                    FileReaderManager fileReaderManager = new FileReaderManager(filename);
                    while (fileReaderManager.getScanner().hasNextLine()) {
                        try {
                            String[] commandAndArgument = fileReaderManager.readCommandAndArgument();
                            String command = commandAndArgument[0];
                            if (commands.containsKey(command)) {
                                if (command.equals("add") || command.equals("add_if_max") || command.equals("add_if_min") || command.equals("remove_lower") || command.equals("update")) {
                                    String name = fileReaderManager.readName();
                                    Long x = fileReaderManager.readCoordinateX();
                                    float y = fileReaderManager.readCoordinateY();
                                    int numberOfParticipants = fileReaderManager.readNumberOfParticipants();
                                    MusicGenre musicGenre = fileReaderManager.readMusicGenre();
                                    long bands = fileReaderManager.readBands();
                                    MusicBand musicBand = validator.getValidatedElement(new MusicBand(name, new Coordinates(x, y), numberOfParticipants, musicGenre, new Label(bands)));
                                    if (musicBand != null) {
                                        switch (command) {
                                            case "add" -> {
                                                cm.add(musicBand);
                                                System.out.println("Элемент успешно добавлен в коллекцию. ");
                                            }
                                            case "add_if_max" -> cm.addIfMax(musicBand);
                                            case "add_if_min" -> cm.addIfMin(musicBand);
                                            case "remove_lower" -> cm.removeLower(musicBand);
                                            case "update" -> {
                                                cm.updateId(musicBand, parseInt(commandAndArgument[1]));
                                                System.out.println("Элемент обновлен. ");
                                            }
                                        }
                                    }
                                } else {
                                    commands.get(command).execute(commandAndArgument);
                                }
                            }
                        } catch (IllegalArgumentException | NoSuchElementException ignored) {}
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден. ");
                } finally {
                    FilesStack.removeFile();
                }
            }
        }
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "execute_script file_name: считать и исполнить скрипт из указанного файла";
    }
}
