package commands;

import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;

/**
 * Command `print_descending`.
 *
 * @author Egorova Varvara
 */

public class PrintDescendingCommand implements Action, Command {
    @Serial
    private final static long serialVersionUID = 10L;

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.printDescending());
    }

    @Override
    public String getCommandName() {
        return "print_descending";
    }
}
