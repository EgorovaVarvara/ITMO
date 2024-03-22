package Commands;

import Collection.CollectionManager;
/**
 * Command `info`.
 *
 * @author Egorova Varvara
 */

public class InfoCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code InfoCommand}.
     * @param cm collection manager
     */
    public InfoCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            cm.info();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "info: вывести информацию о коллекции";
    }
}
