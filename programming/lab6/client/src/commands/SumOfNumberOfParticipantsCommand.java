package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `sum_of_number_of_participants`.
 *
 * @author Egorova Varvara
 */
public class SumOfNumberOfParticipantsCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 15L;

    public SumOfNumberOfParticipantsCommand() {
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
