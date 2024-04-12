package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

/**
 * Command `info`.
 *
 * @author Egorova Varvara
 */

public class InfoCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code InfoCommand}.
     * @param cm collection manager
     */
    public InfoCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     *
     * @param request@return
     */
    @Override
    public Response execute(Request request) {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentException();
        return new Response(ResponseStatus.OK, cm.info() + "\n");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "info: вывести информацию о коллекции";
    }
}
