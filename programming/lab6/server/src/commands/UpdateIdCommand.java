package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.util.Objects;

/**
 * Command `update id {element}`.
 *
 * @author Egorova Varvara
 */

public class UpdateIdCommand implements Command, CollectionEditor {
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code UpdateIdCommand}.
     * @param cm collection manager
     */
    public UpdateIdCommand(CollectionManager cm) {
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
        if (Objects.isNull(request.getMusicBand())) throw new IllegalArgumentException();
        try{
            return new Response(ResponseStatus.OK, cm.updateId(request.getMusicBand(), Integer.parseInt(request.getArgs())));
        } catch (NumberFormatException e){
            return new Response(ResponseStatus.ERROR, "Для команды 'update' требуется целочисленный аргумент.");
        }
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "update id {element}: обновить значение элемента коллекции, id которого равен заданному";
    }
}
