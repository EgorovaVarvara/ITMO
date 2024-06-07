package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `sum_of_number_of_participants`.
 *
 * @author Egorova Varvara
 */
public class SumOfNumberOfParticipantsCommand implements Command{
    @Serial
    private final static long serialVersionUID = 15L;
    private User user;

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.sumOfNumberOfParticipants());
    }

    @Override
    public String getCommandName() {
        return "sum_of_number_of_participants";
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
        return null;
    }
}
