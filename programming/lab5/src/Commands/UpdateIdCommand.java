package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;
/**
 * Command `update id {element}`.
 *
 * @author Egorova Varvara
 */

public class UpdateIdCommand implements Command {
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code UpdateIdCommand}.
     * @param cm collection manager
     */
    public UpdateIdCommand(CollectionManager cm) {
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            try {
                int id = Integer.parseInt(args[1]);
                boolean flag = false;
                for (MusicBand musicBand : cm.getMusicBands()) {
                    if (musicBand.getId() == id) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    MusicBand musicBand = cm.getClientManager().getMusicBand();
                    cm.updateId(musicBand, id);
                } else {
                    System.out.println("Элемента с таким id нет в коллекции. ");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Введены неверные аргументы. Попробуйте еще раз. ");
            }
        } else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "update id {element}: обновить значение элемента коллекции, id которого равен заданному";
    }
}
