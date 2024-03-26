package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;
/**
 * Command `add_if_min {element}`.
 *
 * @author Egorova Varvara
 */

public class AddIfMinCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code AddIfMinCommand}.
     * @param cm collection manager
     */
    public AddIfMinCommand(CollectionManager cm){
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
                cm.addIfMin(musicBand);
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
        return "add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
