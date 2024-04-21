package commands;

import baseClasses.CommandType;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `filter_less_than_number_of_participants numberOfParticipants`.
 *
 * @author Egorova Varvara
 */

public class FilterLessThanNumberOfParticipantsCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 7L;
    private int numberOfParticipants;

    public FilterLessThanNumberOfParticipantsCommand(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
