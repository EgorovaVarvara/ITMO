package commands;

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
    private String user_login;
    /**
     * Constructor of class
     */
    public PrintDescendingCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
