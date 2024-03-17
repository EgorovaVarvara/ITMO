package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

public class AddIfMinCommand implements Command{
    CollectionManager cm;

    public AddIfMinCommand(CollectionManager cm){
        this.cm = cm;
    }
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

    @Override
    public String getDescription() {
        return "add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
