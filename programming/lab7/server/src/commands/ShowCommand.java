package commands;

import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;

/**
 * Command `show`.
 *
 * @author Egorova Varvara
 */

public class ShowCommand implements Command {
    @Serial
    private final static long serialVersionUID = 14L;
    private String user_login;

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.show());
    }

    @Override
    public String getCommandName() {
        return "show";
    }
}
