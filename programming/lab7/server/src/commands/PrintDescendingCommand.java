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

public class PrintDescendingCommand implements Command {
    @Serial
    private final static long serialVersionUID = 10L;
    private String user_login;
    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.printDescending());
    }

    @Override
    public String getCommandName() {
        return "print_descending";
    }
}
