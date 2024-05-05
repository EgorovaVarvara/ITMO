package commands;

import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;

public class DefaultCommand implements Command {
    @Serial
    private final static long serialVersionUID = 4L;
    private String user_login;
    @Override
    public Response run() {
        return new Response(ResponseStatus.INFO, "Неизвестная команда. Введите 'help' чтобы получить список доступных команд. ");
    }

    @Override
    public String getCommandName() {
        return null;
    }
}
