package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

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
    private User user;
    /**
     * Constructor of class
     * @param musicBand
     */
    public AddIfMinCommand(MusicBand musicBand) {
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
