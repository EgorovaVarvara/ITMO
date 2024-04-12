package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.util.Objects;

/**
 * Command `add_if_min {element}`.
 *
 * @author Egorova Varvara
 */

public class AddIfMinCommand implements Command, CollectionEditor{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code AddIfMinCommand}.
     * @param cm collection manager
     */
    public AddIfMinCommand(CollectionManager cm){
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
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды 'add_if_min' требуется объект");
        } else{
            return new Response(ResponseStatus.OK, cm.addIfMin(request.getMusicBand()));
        }
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
