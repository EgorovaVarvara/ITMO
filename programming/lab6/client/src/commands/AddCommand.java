package commands;

import baseClasses.CommandType;
import baseClasses.MusicBand;
import baseClasses.MusicGenre;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `add {element}`.
 *
 * @author Egorova Varvara
 */
public class AddCommand implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 0L;
    private MusicBand musicBand;

    public AddCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
