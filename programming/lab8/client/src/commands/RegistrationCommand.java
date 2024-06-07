package commands;

import baseClasses.MusicBand;
import connectionUtils.User;

import java.io.Serial;

public class RegistrationCommand implements Command{
    @Serial
    private static final long serialVersionUID = 17L;
    private User user;

    public RegistrationCommand(User user){
        this. user = user;
    }
    @Override
    public void setUser(User user) {

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
