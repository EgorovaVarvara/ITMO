package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;


/**
 * Command `add_if_max {element}`.
 *
 * @author Egorova Varvara
 */
public class AddIfMaxCommand implements CollectionEditor, Action, Command {
    @Serial
    private final static long serialVersionUID = 1L;

    private MusicBand musicBand;
    public AddIfMaxCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.addIfMax(musicBand));
    }

    @Override
    public String getCommandName() {
        return "add_if_max";
    }
}
