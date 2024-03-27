package Commands;

import Collection.CollectionManager;
import Console.ConsoleManager; // TODO never used 
/**
 * Command `filter_less_than_number_of_participants numberOfParticipants`.
 *
 * @author Egorova Varvara
 */

public class FilterLessThanNumberOfParticipantsCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm;
    /**
     * Constructor that creates object of {@code FilterLessThanNumberOfParticipantsCommand}.
     * @param cm collection manager
     */
    public FilterLessThanNumberOfParticipantsCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 2){
            try {
                int numberOfParticipants = Integer.parseInt(args[1]);
                cm.filterLessThanNumberOfParticipants(numberOfParticipants);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы, попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов. ");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "filter_less_than_number_of_participants numberOfParticipants: вывести элементы, значение поля numberOfParticipants которых меньше заданного";
    }
}
