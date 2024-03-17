package Commands;

import Collection.CollectionManager;

public class ClearCommand implements Command{
    CollectionManager cm;
    public ClearCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.clear();
            System.out.println("Коллекция очищена. ");
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    @Override
    public String getDescription() {
        return "clear: очистить коллекцию";
    }
}
