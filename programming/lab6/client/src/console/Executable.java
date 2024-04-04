package console;

import errors.CommandRuntimeException;
import errors.ExitObligedException;

public interface Executable {
    void execute(String args) throws IllegalArgumentException, CommandRuntimeException, ExitObligedException;
}
