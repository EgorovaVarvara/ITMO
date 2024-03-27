package Commands;

import Collection.CollectionManager;
/**
 * Command `print_descending`.
 *
 * @author Egorova Varvara
 */

public class PrintDescendingCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code PrintDescendingCommand}.
     * @param cm collection manager
     */
    public PrintDescendingCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            cm.printDescending();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }
    /**
     * @return description of command
     */

    @Override
    public String getDescription() {
        return "print_descending: вывести все элементы коллекции в порядке убывания";
    }
}
