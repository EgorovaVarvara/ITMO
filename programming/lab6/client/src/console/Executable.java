package console;

import connectionUtils.Response;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

public interface Executable {
    Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException;
}
