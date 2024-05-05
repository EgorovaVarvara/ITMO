package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import utils.DataBaseManager;

import java.io.Serial;


/**
 * Command `add_if_max {element}`.
 *
 * @author Egorova Varvara
 */
public class AddIfMaxCommand implements Command {
    @Serial
    private final static long serialVersionUID = 1L;
    private String user_login;

    private MusicBand musicBand;
    public AddIfMaxCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }

    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        int id = dataBaseManager.addIfMax(musicBand);
        if (id == -1) {
            return new Response(ResponseStatus.OK, "Не удалось выполнить команду");
        } else if (id == -2) {
            return new Response(ResponseStatus.ERROR, "Элемент не добавлен в коллекцию (не наибольший) ");
        } else {
            musicBand.setId(id);
            return new Response(ResponseStatus.OK, CollectionManager.addIfMax(musicBand));
        }
    }

    @Override
    public String getCommandName() {
        return "add_if_max";
    }
}
