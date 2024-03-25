package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;
/**
 * Command `add_if_max {element}`.
 *
 * @author Egorova Varvara
 */
public class AddIfMaxCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code AddIfMaxCommand}.
     * @param cm collection manager
     */
    public AddIfMaxCommand(CollectionManager cm){
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
                cm.addIfMax(musicBand);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы. Попробуйте еще раз. ");
            }
        }else throw new IllegalArgumentException("Неверное число аргументов. ");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

}
