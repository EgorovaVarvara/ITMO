package commands;

import baseClasses.CommandType;
import baseClasses.MusicBand;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `add_if_min {element}`.
 *
 * @author Egorova Varvara
 */

public class AddIfMinCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 2L;
    private MusicBand musicBand;

    public AddIfMinCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
