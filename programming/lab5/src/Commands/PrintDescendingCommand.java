package Commands;

import Collection.CollectionManager;

public class PrintDescendingCommand implements Command{
    CollectionManager cm;
    public PrintDescendingCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            cm.printDescending();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }

    @Override
    public String getDescription() {
        return "print_descending: вывести все элементы коллекции в порядке убывания";
    }
}
