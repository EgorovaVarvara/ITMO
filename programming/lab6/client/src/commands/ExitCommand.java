package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `exit`.
 *
 * @author Egorova Varvara
 */

public class ExitCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 6L;

    public ExitCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
