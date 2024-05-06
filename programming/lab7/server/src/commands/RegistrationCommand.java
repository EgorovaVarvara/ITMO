package commands;

import baseClasses.MusicBand;
import connectionUtils.Response;
import connectionUtils.User;
import utils.DataBaseManager;

import java.io.Serial;

public class RegistrationCommand implements Command{
    @Serial
    private static final long serialVersionUID = 17L;
    private User user;

    public RegistrationCommand(User user){
        this. user = user;
    }

    @Override
    public Response run() {
        DataBaseManager dataBaseManager = new DataBaseManager();
        if (user.isExists()) return dataBaseManager.authorisation(user);
        return dataBaseManager.registration(user);
    }

    @Override
    public String getCommandName() {
        return "registration";
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
