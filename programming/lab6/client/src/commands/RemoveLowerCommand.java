package commands;

import baseClasses.MusicBand;
import java.io.Serial;

/**
 * Command `remove_lower {element}`.
 *
 * @author Egorova Varvara
 */
public class RemoveLowerCommand  implements Command {
    @Serial
    private final static long serialVersionUID = 12L;
    /**
     * Music band for adding to collection with deleting lower elements
     */
    private MusicBand musicBand;
    /**
     * Constructor of class
     * @param musicBand
     */

    public RemoveLowerCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
}
