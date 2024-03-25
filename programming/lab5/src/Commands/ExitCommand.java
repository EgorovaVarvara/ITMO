package Commands;

import Collection.CollectionManager;
/**
 * Command `exit`.
 *
 * @author Egorova Varvara
 */

public class ExitCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code ExitCommand}.
     * @param cm collection manager
     */
    public ExitCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.exit();
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "exit: завершить программу (без сохранения в файл)";
    }
}
