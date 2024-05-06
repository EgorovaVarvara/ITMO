package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `print_descending`.
 *
 * @author Egorova Varvara
 */

public class PrintDescendingCommand implements Command {
    @Serial
    private final static long serialVersionUID = 10L;
    private User user;
    /**
     * Constructor of class
     */
    public PrintDescendingCommand() {
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
