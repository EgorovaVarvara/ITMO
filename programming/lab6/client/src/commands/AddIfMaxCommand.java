package commands;

import baseClasses.CommandType;
import baseClasses.MusicBand;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `add_if_max {element}`.
 *
 * @author Egorova Varvara
 */
public class AddIfMaxCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 1L;
    private MusicBand musicBand;

    public AddIfMaxCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
