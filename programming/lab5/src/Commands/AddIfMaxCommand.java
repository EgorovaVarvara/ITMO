package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

public class AddIfMaxCommand implements Command{
    CollectionManager cm;
    public AddIfMaxCommand(CollectionManager cm){
        this.cm = cm;
    }
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

    @Override
    public String getDescription() {
        return "add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

}
