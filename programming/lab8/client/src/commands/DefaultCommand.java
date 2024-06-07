package commands;


import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

public class DefaultCommand implements Command {
    @Serial
    private final static long serialVersionUID = 4L;
    private User user;
    /**
     * Constructor of class
     */
    public DefaultCommand() {
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
