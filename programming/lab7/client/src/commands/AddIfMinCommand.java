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
    private String user_login;
    /**
     * Constructor of class
     * @param musicBand
     */
    public AddIfMinCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
