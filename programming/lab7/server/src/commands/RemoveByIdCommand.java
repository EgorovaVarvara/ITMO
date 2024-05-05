package commands;

import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import utils.DataBaseManager;

import java.io.Serial;

/**
 * Command `remove_by_id id`.
 *
 * @author Egorova Varvara
 */

public class RemoveByIdCommand implements Command{
    @Serial
    private final static long serialVersionUID = 11L;
    private int id;
    private String user_login;
    public RemoveByIdCommand(Integer id){
        this.id = id;
    }
    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.removeObject(id, user_login)) return new Response(ResponseStatus.OK, CollectionManager.removeById(id));
        return new Response(ResponseStatus.OK, "Невозможно удалить элемент, так как он принадлежит другому пользователю.");
    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }
}
