package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `remove_lower {element}`.
 *
 * @author Egorova Varvara
 */
public class RemoveLowerCommand  implements Command {
    @Serial
    private final static long serialVersionUID = 12L;
    private User user;
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
    @Override
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return musicBand;
    }
    @Override
    public Integer getIntArgument() {
        return null;
    }
}
