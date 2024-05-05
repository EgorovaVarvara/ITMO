package commands;

import connectionUtils.User;

import java.io.Serial;

/**
 * Command `filter_less_than_number_of_participants numberOfParticipants`.
 *
 * @author Egorova Varvara
 */

public class FilterLessThanNumberOfParticipantsCommand implements Command {
    @Serial
    private final static long serialVersionUID = 7L;
    private String user_login;
    /**
     * Number of participants by which the collection is sorted
     */
    private int numberOfParticipants;
    /**
     * Constructor of class
     * @param numberOfParticipants
     */
    public FilterLessThanNumberOfParticipantsCommand(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    @Override
    public void setUser(User user) {
        this.user_login = user.getLogin();
    }
}
