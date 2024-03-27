package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;
/**
 * Command `remove_lower {element}`.
 *
 * @author Egorova Varvara
 */
public class RemoveLowerCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code RemoveLowerCommand}.
     * @param cm collection manager
     */
    public RemoveLowerCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            try{
                MusicBand musicBand = cm.getClientManager().getMusicBand();
                cm.removeLower(musicBand);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы. Попробуйте еще раз. ");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
