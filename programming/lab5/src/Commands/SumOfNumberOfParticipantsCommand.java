package Commands;

import Collection.CollectionManager;

public class SumOfNumberOfParticipantsCommand implements Command{
    CollectionManager cm;
    public SumOfNumberOfParticipantsCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.sumOfNumberOfParticipants();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }

    @Override
    public String getDescription() {
        return "sum_of_number_of_participants: вывести сумму значений поля numberOfParticipants для всех элементов коллекции";
    }
}
