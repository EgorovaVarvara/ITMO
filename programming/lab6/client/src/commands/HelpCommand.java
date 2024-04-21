package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `help`.
 *
 * @author Egorova Varvara
 */

public class HelpCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 8L;

    public HelpCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
