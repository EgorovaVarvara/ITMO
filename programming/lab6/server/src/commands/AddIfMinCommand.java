package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;


/**
 * Command `add_if_min {element}`.
 *
 * @author Egorova Varvara
 */

public class AddIfMinCommand implements CollectionEditor, Action, Command {
    @Serial
    private final static long serialVersionUID = 2L;
    MusicBand musicBand;
    /**
     * Constructor that creates object of {@code AddIfMinCommand}.
     * @param cm collection manager
     */
    public AddIfMinCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.addIfMin(musicBand));
    }

    @Override
    public String getCommandName() {
        return "add_if_min";
    }
}
