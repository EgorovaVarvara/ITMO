package Commands;

import Collection.CollectionManager;
/**
 * Command `show`.
 *
 * @author Egorova Varvara
 */

public class ShowCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code ShowCommand}.
     * @param cm collection manager
     */
    public ShowCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.show();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "show: вывести все элементы коллекции";
    }
}
