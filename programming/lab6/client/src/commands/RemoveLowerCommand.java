package commands;

import baseClasses.CommandType;
import baseClasses.MusicBand;
import connectionUtils.Response;
import console.Executable;
import errors.CommandRuntimeException;
import errors.ExitObligedException;

import java.io.Serial;

/**
 * Command `remove_lower {element}`.
 *
 * @author Egorova Varvara
 */
public class RemoveLowerCommand  implements Command, Executable {
    @Serial
    private final static long serialVersionUID = 12L;
    private MusicBand musicBand;

    public RemoveLowerCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    @Override
    public Response execute() throws IllegalArgumentException, CommandRuntimeException, ExitObligedException {
        return null;
    }
}
