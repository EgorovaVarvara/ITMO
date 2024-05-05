package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import utils.DataBaseManager;

import java.io.Serial;

/**
 * Command `add {element}`.
 *
 * @author Egorova Varvara
 */
public class AddCommand implements Command {
    @Serial
    private final static long serialVersionUID = 0L;
    private MusicBand musicBand;
    private String user_login;

    public AddCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }

    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        int id = dataBaseManager.addObject(musicBand);
        if (id != -1) {
            musicBand.setId(id);
            CollectionManager.add(musicBand);
            return new Response(ResponseStatus.OK, "Элемент успешно добавлен в коллекцию. ");
        } else {
            return new Response(ResponseStatus.OK, "Не получилось добавить элемент в коллекцию.");
        }
    }

    @Override
    public String getCommandName() {
        return "add";
    }
}
