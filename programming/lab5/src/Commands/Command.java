package Commands;

/**
 * Interface {@code Command} that implemented by all commands
 */
// TODO трабл в том что интерфейс есть, классы от него имплементируются(и повторяют код), но нигде более контракт Command не используется
public interface Command {
    /**
     * Executes the command.
     * @param args arguments
     */
    public void execute(String[] args); // TODO throws IllegalArgumentException, раз уж команды выбрасывают это исключение
    /**
     * @return description of command
     */
    public String getDescription();
}
