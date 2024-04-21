package commands;

import baseClasses.MusicBand;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.util.Objects;

/**
 * Command `update id {element}`.
 *
 * @author Egorova Varvara
 */

public class UpdateIdCommand implements CollectionEditor, Action, Command {
    @Serial
    private final static long serialVersionUID = 16L;
    private MusicBand musicBand;
    public UpdateIdCommand(MusicBand musicBand){
        this.musicBand = musicBand;
    }
    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, CollectionManager.updateId(musicBand));
    }

    @Override
    public String getCommandName() {
        return "update";
    }
}
