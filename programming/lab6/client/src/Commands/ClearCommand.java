package Commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

/**
 * Command `clear`.
 *
 * @author Egorova Varvara
 */
public class ClearCommand extends Command implements Executable {

    public ClearCommand(CommandType commandType, Object... arguments) {
        super(commandType, arguments);
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
