package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.util.Objects;

/**
 * Command `remove_by_id id`.
 *
 * @author Egorova Varvara
 */

public class RemoveByIdCommand implements Command, CollectionEditor{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code RemoveByIdCommand}.
     * @param cm collection manager
     */
    public RemoveByIdCommand(CollectionManager cm){
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
        if (Objects.isNull(request.getMusicBand())){
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды 'add' требуется объект");
        } else{
            try{
                return new Response(ResponseStatus.OK, cm.removeById(Integer.parseInt(request.getArgs())));
            } catch (NumberFormatException e) {
                return new Response(ResponseStatus.ERROR, "Для команды 'rempve_by_id' требуется целочисленный аргумента");
            }
        }
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "remove_by_id id: удалить элемент из коллекции по его id";
    }
}
