package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

/**
 * Command `print_descending`.
 *
 * @author Egorova Varvara
 */

public class PrintDescendingCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code PrintDescendingCommand}.
     * @param cm collection manager
     */
    public PrintDescendingCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     *
     * @param request@return
     */
    @Override
    public Response execute(Request request) {
        if (request.getArgs().isBlank()) throw  new IllegalArgumentException();
        return new Response(ResponseStatus.OK, cm.printDescending() + "\n");
    }
    /**
     * @return description of command
     */

    @Override
    public String getDescription() {
        return "print_descending: вывести все элементы коллекции в порядке убывания";
    }
}
