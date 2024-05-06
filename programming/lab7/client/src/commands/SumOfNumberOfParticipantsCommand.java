package commands;

import baseClasses.MusicBand;
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
    private User user;
    /**
     * Constructor of class
     */
    public SumOfNumberOfParticipantsCommand() {
    }
    @Override
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return null;
    }
    @Override
    public Integer getIntArgument() {
        return null;
    }
}
