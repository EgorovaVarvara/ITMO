package commands;


import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `exit`.
 *
 * @author Egorova Varvara
 */

public class ExitCommand implements Command {
    @Serial
    private final static long serialVersionUID = 6L;
    private User user;
    /**
     * Constructor of class
     */
    public ExitCommand() {
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
