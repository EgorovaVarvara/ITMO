package Commands;

import Collection.CollectionManager;

public class ShowCommand implements Command{
    CollectionManager cm;
    public ShowCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.show();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }

    @Override
    public String getDescription() {
        return "show: вывести все элементы коллекции";
    }
}
