package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

/**
 * Command `help`.
 *
 * @author Egorova Varvara
 */

public class HelpCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code HelpCommand}.
     * @param cm collection manager
     */
    public HelpCommand(CollectionManager cm){
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
        return new Response(ResponseStatus.OK, cm.help() + "\n");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "help: вывести справку по доступным командам";
    }
}
