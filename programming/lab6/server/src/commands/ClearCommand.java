package commands;

import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Command `clear`.
 *
 * @author Egorova Varvara
 */
public class ClearCommand implements CollectionEditor, Action, Command {
    @Serial
    private final static long serialVersionUID = 3L;

    @Override
    public Response run() {
        CollectionManager.clear();
        return new Response(ResponseStatus.OK, "Коллекция успешно очищена. ");
    }

    @Override
    public String getCommandName() {
        return "clear";
    }
}
