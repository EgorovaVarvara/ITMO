package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.util.Objects;

/**
 * Command `filter_less_than_number_of_participants numberOfParticipants`.
 *
 * @author Egorova Varvara
 */

public class FilterLessThanNumberOfParticipantsCommand implements Command {
    /**
     * @see CollectionManager
     */
    CollectionManager cm;

    /**
     * Constructor that creates object of {@code FilterLessThanNumberOfParticipantsCommand}.
     *
     * @param cm collection manager
     */
    public FilterLessThanNumberOfParticipantsCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Executes the command.
     *
     * @param request@return
     */
    @Override
    public Response execute(Request request) {
        if (request.getArgs().isBlank()) throw new IllegalArgumentException();
        try {
            return new Response(ResponseStatus.OK, cm.filterLessThanNumberOfParticipants(Integer.parseInt(request.getArgs())));
        } catch (NumberFormatException e) {
            return new Response(ResponseStatus.ERROR, "Для команды 'filter_less_than_number_of_participants' требуется целочисленный аргумента");
        }

    }

    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "filter_less_than_number_of_participants numberOfParticipants: вывести элементы, значение поля numberOfParticipants которых меньше заданного";
    }
}
