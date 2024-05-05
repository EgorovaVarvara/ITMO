package commands;

import connectionUtils.User;

import java.io.Serial;

/**
 * `ServiceCommand` class.
 *
 * @author Egorova Varvara
 */
public class ServiceCommand implements Command {
    @Serial
    private final static long serialVersionUID = 13L;
    private String user_login;
    /**
     * Constructor of class
     */
    public ServiceCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
