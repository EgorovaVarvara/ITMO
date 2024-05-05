package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import utils.DataBaseManager;

import java.io.Serial;

/**
 * Command `update id {element}`.
 *
 * @author Egorova Varvara
 */

public class UpdateIdCommand implements Command {
    @Serial
    private final static long serialVersionUID = 16L;
    private MusicBand musicBand;
    private String user_login;
    public UpdateIdCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }
    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.updateObject(musicBand, user_login)) return new Response(ResponseStatus.OK, CollectionManager.updateId(musicBand));
        return new Response(ResponseStatus.OK, "Вы не можете изменить этот элемент, так как он принадлежит другому пользователю (сорян)");
    }

    @Override
    public String getCommandName() {
        return "update";
    }
}
