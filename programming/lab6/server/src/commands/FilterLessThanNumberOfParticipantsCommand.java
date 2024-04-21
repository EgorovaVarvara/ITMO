package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.util.Objects;

/**
 * Command `filter_less_than_number_of_participants numberOfParticipants`.
 *
 * @author Egorova Varvara
 */

public class FilterLessThanNumberOfParticipantsCommand implements Action, Command{
    @Serial
    private final static long serialVersionUID = 7L;
    private int numberOfParticipants;
    public FilterLessThanNumberOfParticipantsCommand(Integer numberOfParticipants){
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.filterLessThanNumberOfParticipants(numberOfParticipants));
    }

    @Override
    public String getCommandName() {
        return "filter_less_than_number_of_participants";
    }
}
