package commands;

import baseClasses.MusicBand;
import connectionUtils.Response;
import connectionUtils.User;

import java.io.Serial;
import java.io.Serializable;

public interface Command extends Serializable {
    @Serial
    long serialVersionUID = 12345L;

    Response run();

    String getCommandName();
    User getUser();
    MusicBand getMusicband();
    Integer getIntArgument();

}
