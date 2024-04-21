package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.util.Objects;

/**
 * Command `remove_lower {element}`.
 *
 * @author Egorova Varvara
 */
public class RemoveLowerCommand implements CollectionEditor, Action, Command{
    @Serial
    private final static long serialVersionUID = 12L;
    private MusicBand musicBand;
    public RemoveLowerCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }
    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.removeLower(musicBand));
    }

    @Override
    public String getCommandName() {
        return "remove_lower";
    }
}
