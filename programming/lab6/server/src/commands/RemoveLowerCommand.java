package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.util.Objects;

/**
 * Command `remove_lower {element}`.
 *
 * @author Egorova Varvara
 */
public class RemoveLowerCommand implements Command, CollectionEditor{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code RemoveLowerCommand}.
     * @param cm collection manager
     */
    public RemoveLowerCommand(CollectionManager cm){
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
        if (Objects.isNull(request.getMusicBand())){
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды 'remove_lower' требуется объект");
        } else {
            return new Response(ResponseStatus.OK, cm.removeLower(request.getMusicBand()));
        }
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
