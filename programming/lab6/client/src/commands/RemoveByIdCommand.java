package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `remove_by_id id`.
 *
 * @author Egorova Varvara
 */

public class RemoveByIdCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 11L;
    private int id;

    public RemoveByIdCommand(int id) {
        this.id = id;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
