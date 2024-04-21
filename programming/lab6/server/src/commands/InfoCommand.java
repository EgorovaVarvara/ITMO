package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;

/**
 * Command `info`.
 *
 * @author Egorova Varvara
 */

public class InfoCommand implements Action, Command{
    @Serial
    private final static long serialVersionUID = 9L;

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.info());
    }

    @Override
    public String getCommandName() {
        return "info";
    }
}
