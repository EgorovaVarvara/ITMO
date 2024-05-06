package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `show`.
 *
 * @author Egorova Varvara
 */

public class ShowCommand implements Command {
    @Serial
    private final static long serialVersionUID = 14L;
    private User user;

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.show());
    }

    @Override
    public String getCommandName() {
        return "show";
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
