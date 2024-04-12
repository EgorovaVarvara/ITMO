package Commands;

import baseClasses.CommandType;
import connectionUtils.Response;

import java.io.Serializable;

/**
 * Interface {@code Command} that implemented by all commands
 */
public class Command implements Serializable {
    private final CommandType commandType;
    private final Object[] arguments;
    public Command(CommandType commandType, Object... arguments){
        this.commandType = commandType;
        this.arguments = arguments;
    }
}
