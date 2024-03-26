package Commands;

/**
 * Interface {@code Command} that implemented by all commands
 */
public interface Command {
    /**
     * Executes the command.
     * @param args arguments
     */
    public void execute(String[] args);
    /**
     * @return description of command
     */
    public String getDescription();
}
