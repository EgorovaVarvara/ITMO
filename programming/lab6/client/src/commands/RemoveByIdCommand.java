package commands;

import java.io.Serial;

/**
 * Command `remove_by_id id`.
 *
 * @author Egorova Varvara
 */

public class RemoveByIdCommand implements Command {
    @Serial
    private final static long serialVersionUID = 11L;
    /**
     * Id by which collection is sorted
     */
    private int id;
    /**
     * Constructor of class
     * @param id
     */
    public RemoveByIdCommand(int id) {
        this.id = id;
    }
}
