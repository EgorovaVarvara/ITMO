package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `info`.
 *
 * @author Egorova Varvara
 */

public class InfoCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 9L;

    public InfoCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
