package commands;


import connectionUtils.User;

import java.io.Serial;

public class DefaultCommand implements Command {
    @Serial
    private final static long serialVersionUID = 4L;
    private String user_login;
    /**
     * Constructor of class
     */
    public DefaultCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
