package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;

/**
 * Command `show`.
 *
 * @author Egorova Varvara
 */

public class ShowCommand implements Action, Command {
    @Serial
    private final static long serialVersionUID = 14L;

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.show());
    }

    @Override
    public String getCommandName() {
        return "show";
    }
}
