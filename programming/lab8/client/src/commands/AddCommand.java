package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `add {element}`.
 *
 * @author Egorova Varvara
 */
public class AddCommand implements Command {
    @Serial
    private final static long serialVersionUID = 0L;
    /**
     * Music band for adding to collection
     */
    private MusicBand musicBand;
    private User user;

    /**
     * Constructor of class
     * @param musicBand
     */
    public AddCommand(MusicBand musicBand) {
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
