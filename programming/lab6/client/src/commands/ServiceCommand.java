package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

public class ServiceCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 13L;

    public ServiceCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
