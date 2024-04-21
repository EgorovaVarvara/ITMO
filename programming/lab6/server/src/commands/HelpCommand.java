package commands;

import baseClasses.CommandType;
import collection.CollectionManager;
import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Command `help`.
 *
 * @author Egorova Varvara
 */

public class HelpCommand implements Action, Command{
    @Serial
    private final static long serialVersionUID = 8L;

    @Override
    public Response run() {
        return new Response(ResponseStatus.OK, Arrays.stream(CommandType.values()).
                map(CommandType::getDescription).
                filter(description -> !description.isEmpty()).
                collect(Collectors.joining("\n")));
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
