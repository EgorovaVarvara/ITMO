package commands;

import baseClasses.CommandType;
import baseClasses.MusicBand;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;
import java.util.Map;

/**
 * Command `update id {element}`.
 *
 * @author Egorova Varvara
 */

public class UpdateIdCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 16L;
    private MusicBand musicBand;
    public UpdateIdCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
