package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

/**
 * Command `sum_of_number_of_participants`.
 *
 * @author Egorova Varvara
 */
public class SumOfNumberOfParticipantsCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code SumOfNumberOfParticipantsCommand}.
     * @param cm collection manager
     */
    public SumOfNumberOfParticipantsCommand(CollectionManager cm){
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
        return new Response(ResponseStatus.OK, cm.sumOfNumberOfParticipants());
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "sum_of_number_of_participants: вывести сумму значений поля numberOfParticipants для всех элементов коллекции";
    }
}
