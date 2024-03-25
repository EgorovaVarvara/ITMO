package Commands;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

// TODO пимер команды без интерфейса, если он нигде не используется 
public class ExampleAddCommand extends AbstractCommand {

    public ExampleAddCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
       if (args.length == 1) {
            try{
                MusicBand musicBand = this.collectionManager.getClientManager().getMusicBand();
                boolean flag = true;
                for (MusicBand musicBand1 : this.collectionManager.getMusicBands()){
                    if (musicBand1.equals(musicBand)) {
                        flag = false;
                    }
                }
                if (flag){
                    this.collectionManager.add(musicBand);
                    System.out.println("Элемент успешно добавлен в коллекцию.");
                }else{
                    System.out.println("Введеный элемент уже есть в коллекции. ");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы. Попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    @Override
    public String getDescription() {
        return "add {element}: добавить новый элемент в коллекцию";
    }
    
}
