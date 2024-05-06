package commands;


import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Interface {@code Command} that implemented by all commands
 */
public interface Command extends Serializable {
    @Serial
    long serialVersionUID = 12345L;
    void setUser(User user);
    User getUser();
    MusicBand getMusicband();
    Integer getIntArgument();
}
