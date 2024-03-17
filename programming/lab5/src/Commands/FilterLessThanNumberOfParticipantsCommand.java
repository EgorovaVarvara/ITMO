package Commands;

import Collection.CollectionManager;
import Console.ConsoleManager;

public class FilterLessThanNumberOfParticipantsCommand implements Command{
    CollectionManager cm;
    public FilterLessThanNumberOfParticipantsCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 2){
            try {
                int numberOfParticipants = Integer.parseInt(args[1]);
                cm.filterLessThanNumberOfParticipants(numberOfParticipants);
            }catch (IllegalArgumentException e){
                System.out.println("Wrong arguments. Try again.");
            }
        }else throw new IllegalArgumentException("Wrong number of arguments.");

    }

    @Override
    public String getDescription() {
        return "filter_less_than_number_of_participants numberOfParticipants: вывести элементы, значение поля numberOfParticipants которых меньше заданного";
    }
}
