package commands;


import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;
import java.util.ArrayList;

/**
 * Command `execute_script file_name`.
 *
 * @author Egorova Varvara
 */

public class ExecuteScriptCommand implements Command {
    @Serial
    private final static long serialVersionUID = 5L;
    private User user;

    /**
     * List of commands for executing
     */
    private ArrayList<Command> commands;
    /**
     * Constructor of class
     * @param commands ArrayList of commands
     */
    public ExecuteScriptCommand(ArrayList<Command> commands) {
        this.commands = commands;
    }
    @Override
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public User getUser() {
        return user;
    }

    @Override
    public MusicBand getMusicband() {
        return null;
    }
    @Override
    public Integer getIntArgument() {
        return null;
    }
}
