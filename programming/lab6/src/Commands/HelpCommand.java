package Commands;

import Collection.CollectionManager;
/**
 * Command `help`.
 *
 * @author Egorova Varvara
 */

public class HelpCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code HelpCommand}.
     * @param cm collection manager
     */
    public HelpCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            cm.help();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "help: вывести справку по доступным командам";
    }
}
