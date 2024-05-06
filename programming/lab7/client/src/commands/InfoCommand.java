package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `info`.
 *
 * @author Egorova Varvara
 */

public class InfoCommand implements Command {
    @Serial
    private final static long serialVersionUID = 9L;
    private User user;
    /**
     * Constructor of class
     */
    public InfoCommand() {
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
        return null;
    }
    @Override
    public Integer getIntArgument() {
        return null;
    }
}
