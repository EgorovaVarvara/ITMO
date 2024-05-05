package commands;

import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import utils.DataBaseManager;

import java.io.Serial;
import java.util.HashSet;

/**
 * Command `clear`.
 *
 * @author Egorova Varvara
 */
public class ClearCommand implements Command {
    @Serial
    private final static long serialVersionUID = 3L;
    private String user_login;

    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        HashSet<Integer> ids = dataBaseManager.clear(user_login);
        if (ids.isEmpty()){
            return new Response(ResponseStatus.OK, "Коллекция не содержит элементов, которые вы можете удалить");
        }
        for (int id : ids){
            CollectionManager.removeById(id);
        }
        return new Response(ResponseStatus.OK, "Из коллекции удалены ваши элементы");
    }

    @Override
    public String getCommandName() {
        return "clear";
    }
}
