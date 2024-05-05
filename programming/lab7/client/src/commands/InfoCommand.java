package commands;

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
    private String user_login;
    /**
     * Constructor of class
     */
    public InfoCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
