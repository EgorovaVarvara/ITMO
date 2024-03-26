package Commands;

import Collection.CollectionManager;
/**
 * Command `save`.
 *
 * @author Egorova Varvara
 */
public class SaveCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code SaveCommand}.
     * @param cm collection manager
     */
    public SaveCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.save();
        } else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "save: сохранить коллекцию в файл";
    }
}
