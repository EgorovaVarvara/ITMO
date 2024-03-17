package Commands;

import Collection.CollectionManager;

public class ExitCommand implements Command{
    CollectionManager cm;
    public ExitCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.exit();
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }

    @Override
    public String getDescription() {
        return "exit: завершить программу (без сохранения в файл)";
    }
}
