package commands;

import connectionUtils.Response;

import java.io.Serial;
import java.io.Serializable;

public interface Command extends Serializable {
    @Serial
    long serialVersionUID = 12345L;

    Response run();

    String getCommandName();

}
