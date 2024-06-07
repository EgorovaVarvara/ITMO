package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `filter_less_than_number_of_participants numberOfParticipants`.
 *
 * @author Egorova Varvara
 */

public class FilterLessThanNumberOfParticipantsCommand implements Command{
    @Serial
    private final static long serialVersionUID = 7L;
    private int numberOfParticipants;
    private User user;
    public FilterLessThanNumberOfParticipantsCommand(Integer numberOfParticipants){
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public Response run() {
        var response = new Response(ResponseStatus.OK, "ok");
        response.setCollection(CollectionManager.filterLessThanNumberOfParticipants(numberOfParticipants));
        return response;
    }

    @Override
    public String getCommandName() {
        return "filter_less_than_number_of_participants";
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return null;
    }

    @Override
    public Integer getIntArgument() {
        return numberOfParticipants;
    }
}
