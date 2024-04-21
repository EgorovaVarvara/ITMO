package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `clear`.
 *
 * @author Egorova Varvara
 */
public class ClearCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 3L;

    public ClearCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
