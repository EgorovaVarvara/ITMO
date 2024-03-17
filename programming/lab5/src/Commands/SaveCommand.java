package Commands;

import Collection.CollectionManager;

public class SaveCommand implements Command{
    CollectionManager cm;
    public SaveCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        cm.save();
    }

    @Override
    public String getDescription() {
        return "save: сохранить коллекцию в файл";
    }
}
