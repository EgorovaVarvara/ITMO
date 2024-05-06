package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `info`.
 *
 * @author Egorova Varvara
 */

public class InfoCommand implements Command{
    @Serial
    private final static long serialVersionUID = 9L;
    private User user;
    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.info());
    }

    @Override
    public String getCommandName() {
        return "info";
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
