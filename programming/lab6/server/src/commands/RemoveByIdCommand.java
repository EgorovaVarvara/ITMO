package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.util.Objects;

/**
 * Command `remove_by_id id`.
 *
 * @author Egorova Varvara
 */

public class RemoveByIdCommand implements CollectionEditor, Action, Command{
    @Serial
    private final static long serialVersionUID = 11L;
    private int id;
    public RemoveByIdCommand(Integer id){
        this.id = id;
    }
    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.removeById(id));
    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }
}
