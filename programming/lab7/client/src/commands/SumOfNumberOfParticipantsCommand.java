package commands;

import connectionUtils.User;

import java.io.Serial;

/**
 * Command `sum_of_number_of_participants`.
 *
 * @author Egorova Varvara
 */
public class SumOfNumberOfParticipantsCommand implements Command {
    @Serial
    private final static long serialVersionUID = 15L;
    private String user_login;
    /**
     * Constructor of class
     */
    public SumOfNumberOfParticipantsCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
