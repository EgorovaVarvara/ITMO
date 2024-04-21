package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `show`.
 *
 * @author Egorova Varvara
 */

public class ShowCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 14L;

    public ShowCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
