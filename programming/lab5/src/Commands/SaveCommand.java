package Commands;

import Collection.CollectionManager;

public class SaveCommand implements Command{
    CollectionManager cm;
    public SaveCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.save();
        } else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    @Override
    public String getDescription() {
        return "save: сохранить коллекцию в файл";
    }
}
