package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

/**
 * Command `remove_by_id id`.
 *
 * @author Egorova Varvara
 */

public class RemoveByIdCommand implements Command {
    @Serial
    private final static long serialVersionUID = 11L;
    private User user;
    /**
     * Id by which collection is sorted
     */
    private int id;
    /**
     * Constructor of class
     * @param id
     */
    public RemoveByIdCommand(int id) {
        this.id = id;
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
        return id;
    }
}
