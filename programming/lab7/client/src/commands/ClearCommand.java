package commands;


import connectionUtils.User;

import java.io.Serial;

/**
 * Command `clear`.
 *
 * @author Egorova Varvara
 */
public class ClearCommand implements Command {
    @Serial
    private final static long serialVersionUID = 3L;
    private String user_login;
    /**
     * Constructor of class
     */
    public ClearCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
