package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.util.Objects;

/**
 * Command `add_if_max {element}`.
 *
 * @author Egorova Varvara
 */
public class AddIfMaxCommand implements Command, CollectionEditor{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code AddIfMaxCommand}.
     * @param cm collection manager
     */
    public AddIfMaxCommand(CollectionManager cm){
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
        if (Objects.isNull(request.getMusicBand())) {
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды 'add_if_max' требуется объект");
        } else {
            return new Response(ResponseStatus.OK, cm.addIfMax(request.getMusicBand()));
        }
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

}
