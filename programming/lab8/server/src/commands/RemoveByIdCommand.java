package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;
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
    private User user;
    public RemoveByIdCommand(Integer id){
        this.id = id;
    }
    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        int result = dataBaseManager.removeObject(id, user.getLogin());
        if (result == 1){
            return new Response(ResponseStatus.OK, CollectionManager.removeById(id));
        } else if (result == 0){
            return new Response(ResponseStatus.WRONG_USER, "not yours");
        } else {
            return new Response(ResponseStatus.ERROR, "not found");
        }
//        if (dataBaseManager.removeObject(id, user.getLogin())) return new Response(ResponseStatus.OK, CollectionManager.removeById(id));
//        return new Response(ResponseStatus.OK, "Невозможно удалить элемент, так как он принадлежит другому пользователю либо элемента с таким id не существует.");
    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return null;
    }

    @Override
    public Integer getIntArgument() {
        return id;
    }
}
