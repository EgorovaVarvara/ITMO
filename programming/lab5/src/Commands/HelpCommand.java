package Commands;

import Collection.CollectionManager;

public class HelpCommand implements Command{
    CollectionManager cm;
    public HelpCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            cm.help();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }

    @Override
    public String getDescription() {
        return "help: вывести справку по доступным командам";
    }
}
