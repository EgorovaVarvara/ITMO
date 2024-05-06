package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `print_descending`.
 *
 * @author Egorova Varvara
 */

public class PrintDescendingCommand implements Command {
    @Serial
    private final static long serialVersionUID = 10L;
    private User user;
    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.printDescending());
    }

    @Override
    public String getCommandName() {
        return "print_descending";
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
