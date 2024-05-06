package commands;

import baseClasses.MusicBand;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import connectionUtils.User;

import java.io.Serial;

public class DefaultCommand implements Command {
    @Serial
    private final static long serialVersionUID = 4L;
    private User user;
    @Override
    public Response run() {
        return new Response(ResponseStatus.INFO, "Неизвестная команда. Введите 'help' чтобы получить список доступных команд. ");
    }

    @Override
    public String getCommandName() {
        return null;
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
