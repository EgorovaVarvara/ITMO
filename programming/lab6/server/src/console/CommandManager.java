package console;

import collection.CollectionManager;
import commands.*;
import commands.Command;
import connectionUtils.Request;
import connectionUtils.Response;
import exceptions.NoSuchCommandException;
import fileManager.FileManager;

import java.util.HashMap;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * The {@code CommandManager} class manage commands executing.
 *
 * @author Egorova Varvara
 */
public class CommandManager {
    /**
     * Condition of program.
     */
    private boolean isWorking = true;
    /**
     * HashMap with all commands.
     */
    private static final HashMap<String, Command> commands = new HashMap<>();
    /**
     * Name of file with main collection.
     */
    private final FileManager fileManager;
    static final Logger commandManagerLogger = LogManager.getLogManager().getLogger(String.valueOf(CommandManager.class));


    /**
     * Constructor that creates object of class {@code CommandManager} and fills commands HashMap.
     * @param collectionManager
     * @see CollectionManager
     */
    public CommandManager(CollectionManager collectionManager, FileManager fileManager){
        commands.put("help", new HelpCommand(collectionManager));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateIdCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(collectionManager));
        commands.put("exit", new ExitCommand(collectionManager));
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("sum_of_number_of_participants", new SumOfNumberOfParticipantsCommand(collectionManager));
        commands.put("filter_less_than_number_of_participants", new FilterLessThanNumberOfParticipantsCommand(collectionManager));
        commands.put("print_descending", new PrintDescendingCommand(collectionManager));
        this.fileManager = fileManager;
    }
    /**
     * Use to get commands HasMap.
     * @return commands HashMap
     */
    public static HashMap<String, Command> getCommands(){
        return commands;
    }

    /**
     * Returns condition of program.
     * @return boolean
     */
    public boolean getWork(){
        return this.isWorking;
    }

    public Response execute(Request request) throws Exception {
        Command command = commands.get(request.getCommandName());
        if (command == null) {
            throw new NoSuchCommandException();
        }
        Response response = command.execute(request);
        if (command instanceof CollectionEditor) {
            commandManagerLogger.info("Файл обновлен");
            fileManager.saveToJson();
        }
        return response;
    }
}
