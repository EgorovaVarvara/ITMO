package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;
/**
 * Command `remove_by_id id`.
 *
 * @author Egorova Varvara
 */

public class RemoveByIdCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code RemoveByIdCommand}.
     * @param cm collection manager
     */
    public RemoveByIdCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2){
            try {
                int id = Integer.parseInt(args[1]);
                boolean flag = false;
                for (MusicBand musicBand : cm.getMusicBands()){
                    if (musicBand.getId() == id) {
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    cm.removeById(id);
                }else{
                    System.out.println("Элемента с таким id нет в коллекции. ");
                }
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
        return "remove_by_id id: удалить элемент из коллекции по его id";
    }
}
