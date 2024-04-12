package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.util.Objects;

/**
 * Command `add {element}`.
 *
 * @author Egorova Varvara
 */
public class AddCommand implements Command, CollectionEditor{
    /**
     * @see CollectionManager
     */
    CollectionManager collectionManager;

    /**
     * Constructor that creates object of {@code AddCommand}.
     * @param cm collection manager
     */
    public AddCommand(CollectionManager cm){
        this.collectionManager = cm;
    }

    /**
     * Executes the command.
     *
     * @param request@return
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentException{
        if (!request.getArgs().isBlank()) throw new IllegalArgumentException();
        if (Objects.isNull(request.getMusicBand())){
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды 'add' требуется объект");
        } else{
            collectionManager.add(request.getMusicBand());
            return new Response(ResponseStatus.OK, "Объект успешно добавлен\n");
        }
    }

    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "add {element}: добавить новый элемент в коллекцию";
    }
}
