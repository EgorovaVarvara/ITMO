package commands;

import baseClasses.MusicBand;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.Serial;
import java.util.ArrayList;


/**
 * Command `execute_script file_name`.
 *
 * @author Egorova Varvara
 */

public class ExecuteScriptCommand implements Command {
    @Serial
    private final static long serialVersionUID = 5L;
    private final ArrayList<Command> commandStack;
    private User user;
    public ExecuteScriptCommand(ArrayList<Command> commands){
        this.commandStack = commands;
    }

    @Override
    public Response run() {
        if (commandStack.isEmpty()) return new Response(ResponseStatus.ERROR, "The command queue is empty. ");
        StringBuilder output = new StringBuilder();
        commandStack.forEach(command -> output.append(command.run().getResponse()).append("\n"));
        return new Response(ResponseStatus.OK, output.substring(0, output.length() - 1));
    }

    @Override
    public String getCommandName() {
        return "execute_script";
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return null;
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }
}
