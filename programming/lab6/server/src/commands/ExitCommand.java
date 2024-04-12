package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

/**
 * Command `exit`.
 *
 * @author Egorova Varvara
 */

public class ExitCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code ExitCommand}.
     * @param cm collection manager
     */
    public ExitCommand(CollectionManager cm){
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
        return new Response(ResponseStatus.EXIT);
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "exit: завершить программу (без сохранения в файл)";
    }
}
