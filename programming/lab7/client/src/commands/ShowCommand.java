package commands;

import connectionUtils.User;

import java.io.Serial;

/**
 * Command `show`.
 *
 * @author Egorova Varvara
 */

public class ShowCommand implements Command {
    @Serial
    private final static long serialVersionUID = 14L;
    private String user_login;
    /**
     * Constructor of class
     */
    public ShowCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
