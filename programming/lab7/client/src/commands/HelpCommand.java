package commands;

import connectionUtils.User;

import java.io.Serial;

/**
 * Command `help`.
 *
 * @author Egorova Varvara
 */

public class HelpCommand implements Command {
    @Serial
    private final static long serialVersionUID = 8L;
    private String user_login;
    /**
     * Constructor of class
     */
    public HelpCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
