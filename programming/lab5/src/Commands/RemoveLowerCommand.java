package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

public class RemoveLowerCommand implements Command{
    CollectionManager cm;
    public RemoveLowerCommand(CollectionManager cm){
        this.cm = cm;
    }
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

    @Override
    public String getDescription() {
        return "remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
