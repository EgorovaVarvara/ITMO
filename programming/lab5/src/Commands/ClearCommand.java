package Commands;

import Collection.CollectionManager;
/**
 * Command `clear`.
 *
 * @author Egorova Varvara
 */
public class ClearCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code CLearCommand}.
     * @param cm collection manager
     */
    public ClearCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.clear();
            System.out.println("Коллекция очищена. ");
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "clear: очистить коллекцию";
    }
}
