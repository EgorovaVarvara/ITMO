package commands;


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
    private String user_login;
    /**
     * Constructor of class
     */
    public ExitCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
