package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `update id {element}`.
 *
 * @author Egorova Varvara
 */

public class UpdateIdCommand implements Command {
    @Serial
    private final static long serialVersionUID = 16L;
    private User user;
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
