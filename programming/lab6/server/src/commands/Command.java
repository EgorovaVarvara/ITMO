package commands;

import connectionUtils.Request;
import connectionUtils.Response;

/**
 * Interface {@code Command} that implemented by all commands
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param request@return
     */
    public Response execute(Request request);
    /**
     * @return description of command
     */
    public String getDescription();
}
