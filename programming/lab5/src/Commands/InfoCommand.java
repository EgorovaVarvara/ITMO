package Commands;

import Collection.CollectionManager;

public class InfoCommand implements Command{
    CollectionManager cm;
    public InfoCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            cm.info();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }

    @Override
    public String getDescription() {
        return "info: вывести информацию о коллекции";
    }
}
