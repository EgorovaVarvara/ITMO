package commands;

import baseClasses.MusicBand;

import java.io.Serial;

/**
 * Command `update id {element}`.
 *
 * @author Egorova Varvara
 */

public class UpdateIdCommand implements Command {
    @Serial
    private final static long serialVersionUID = 16L;
    /**
     * Music band for updating in collection
     */
    private MusicBand musicBand;
    /**
     * Constructor of class
     */
    public UpdateIdCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
}
