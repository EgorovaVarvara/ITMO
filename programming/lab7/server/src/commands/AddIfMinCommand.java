package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import utils.DataBaseManager;

import java.io.Serial;


/**
 * Command `add_if_min {element}`.
 *
 * @author Egorova Varvara
 */

public class AddIfMinCommand implements Command {
    @Serial
    private final static long serialVersionUID = 2L;
    MusicBand musicBand;
    private String user_login;
    /**
     * Constructor that creates object of {@code AddIfMinCommand}.
     * @param cm collection manager
     */
    public AddIfMinCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }

    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        int id = dataBaseManager.addIfMin(musicBand);
        if (id == -1) {
            return new Response(ResponseStatus.OK, "Не удалось выполнить команду");
        } else if (id == -2) {
            return new Response(ResponseStatus.ERROR, "Элемент не добавлен в коллекцию (не наименьший) ");
        } else {
            musicBand.setId(id);
            return new Response(ResponseStatus.OK, CollectionManager.addIfMin(musicBand));
        }
    }

    @Override
    public String getCommandName() {
        return "add_if_min";
    }
}
