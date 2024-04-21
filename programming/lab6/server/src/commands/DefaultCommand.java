package commands;

import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

public class DefaultCommand implements Action, Command {
    @Serial
    private final static long serialVersionUID = 4L;
    @Override
    public Response run() {
        return new Response(ResponseStatus.INFO, "Неизвестная команда. Введите 'help' чтобы получить список доступных команд. ");
    }

    @Override
    public String getCommandName() {
        return null;
    }
}
