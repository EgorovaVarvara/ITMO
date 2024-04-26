package commands;


import java.io.Serial;
import java.io.Serializable;
/**
 * Interface {@code Command} that implemented by all commands
 */
public interface Command extends Serializable {
    @Serial
    long serialVersionUID = 12345L;

}
