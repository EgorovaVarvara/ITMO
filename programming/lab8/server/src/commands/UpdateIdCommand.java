package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;
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
    private User user;

    public UpdateIdCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (dataBaseManager.updateObject(musicBand, user.getLogin())) {
            if (CollectionManager.updateId(musicBand)) {
                return new Response(ResponseStatus.OK, "ok");
            } else {
                return new Response(ResponseStatus.ERROR, "ne ok");
            }
        }
        return new Response(ResponseStatus.WRONG_USER, "Вы не можете изменить этот элемент, так как он принадлежит другому пользователю (сорян)");
    }

    @Override
    public String getCommandName() {
        return "update";
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return musicBand;
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }
}
