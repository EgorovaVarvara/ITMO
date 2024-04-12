package Commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

public class DefaultCommand extends Command implements Executable {

    public DefaultCommand(CommandType commandType, Object... arguments) {
        super(commandType, arguments);
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
