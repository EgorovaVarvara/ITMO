package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

import java.util.ArrayList;

/**
 * Command `add {element}`.
 *
 * @author Egorova Varvara
 */
public class AddCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;

    /**
     * Constructor that creates object of {@code AddCommand}.
     * @param cm collection manager
     */
    public AddCommand(CollectionManager cm){
        this.cm = cm;
    }

    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            try{
                MusicBand musicBand = cm.getClientManager().getMusicBand();
                boolean flag = true;
                for (MusicBand musicBand1 : cm.getMusicBands()){
                    if (musicBand1.equals(musicBand)) {
                        flag = false;
                    }
                }
                if (flag){
                    cm.add(musicBand);
                    System.out.println("Элемент успешно добавлен в коллекцию.");
                }else{
                    System.out.println("Введеный элемент уже есть в коллекции. ");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы. Попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "add {element}: добавить новый элемент в коллекцию";
    }
}
