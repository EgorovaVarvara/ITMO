package commands;

import baseClasses.MusicBand;

import java.io.Serial;

/**
 * Command `add_if_min {element}`.
 *
 * @author Egorova Varvara
 */

public class AddIfMinCommand implements Command {
    @Serial
    private final static long serialVersionUID = 2L;
    /**
     * Music band for adding to collection
     */
    private MusicBand musicBand;
    /**
     * Constructor of class
     * @param musicBand
     */
    public AddIfMinCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

}
