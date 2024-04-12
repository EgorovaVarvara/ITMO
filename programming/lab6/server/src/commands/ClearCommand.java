package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

/**
 * Command `clear`.
 *
 * @author Egorova Varvara
 */
public class ClearCommand implements Command, CollectionEditor{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code CLearCommand}.
     * @param cm collection manager
     */
    public ClearCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     *
     * @param request
     * @return
     */
    @Override
    public Response execute(Request request) {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentException();
        cm.clear();
        return new Response(ResponseStatus.OK, "Коллекция очищена\n");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "clear: очистить коллекцию";
    }
}
