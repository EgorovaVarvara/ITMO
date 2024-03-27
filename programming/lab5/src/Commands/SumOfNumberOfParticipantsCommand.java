package Commands;

import Collection.CollectionManager;
/**
 * Command `sum_of_number_of_participants`.
 *
 * @author Egorova Varvara
 */
public class SumOfNumberOfParticipantsCommand implements Command{
    /**
     * @see CollectionManager
     */
    CollectionManager cm; // TODO модификатор доступа?
    /**
     * Constructor that creates object of {@code SumOfNumberOfParticipantsCommand}.
     * @param cm collection manager
     */
    public SumOfNumberOfParticipantsCommand(CollectionManager cm){
        this.cm = cm;
    }
    /**
     * Executes the command.
     * @param args arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            cm.sumOfNumberOfParticipants();
        }else throw new IllegalArgumentException("Wrong number of arguments.");
    }
    /**
     * @return description of command
     */
    @Override
    public String getDescription() {
        return "sum_of_number_of_participants: вывести сумму значений поля numberOfParticipants для всех элементов коллекции";
    }
}
