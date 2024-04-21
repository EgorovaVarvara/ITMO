package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `print_descending`.
 *
 * @author Egorova Varvara
 */

public class PrintDescendingCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 10L;


    public PrintDescendingCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
