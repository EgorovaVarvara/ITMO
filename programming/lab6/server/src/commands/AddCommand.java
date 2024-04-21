package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * Command `add {element}`.
 *
 * @author Egorova Varvara
 */
public class AddCommand implements CollectionEditor, Action, Command {
    @Serial
    private final static long serialVersionUID = 0L;
    private MusicBand musicBand;

    public AddCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }

    @Override
    public Response run() {
        CollectionManager.add(musicBand);
        return new Response(ResponseStatus.OK, "Элемент успешно добавлен в коллекцию. ");
    }

    @Override
    public String getCommandName() {
        return "add";
    }
}
