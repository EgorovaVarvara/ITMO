package commands;

import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;
import java.util.ArrayList;

/**
 * Command `execute_script file_name`.
 *
 * @author Egorova Varvara
 */

public class ExecuteScriptCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 5L;
    private ArrayList<Command> commands;
    public ExecuteScriptCommand(ArrayList<Command> commands) {
        this.commands = commands;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
