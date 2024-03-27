package Commands;

import Collection.CollectionManager;

//TODO к примеру как убрать повторяющийся код
public abstract class AbstractCommand {
    protected CollectionManager collectionManager;

    public AbstractCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    abstract public void execute(String[] args) throws IllegalArgumentException;

    abstract public String getDescription();
}
