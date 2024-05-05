package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `add_if_max {element}`.
 *
 * @author Egorova Varvara
 */
public class AddIfMaxCommand implements Command {
    @Serial
    private final static long serialVersionUID = 1L;
    /**
     * Music band for adding to collection
     */
    private MusicBand musicBand;
    private String user_login;

    /**
     * Constructor of class
     * @param musicBand
     */
    public AddIfMaxCommand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
