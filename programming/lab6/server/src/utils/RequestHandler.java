package utils;

import connectionUtils.Request;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;
import console.CommandManager;
import exceptions.CommandRuntimeException;
import exceptions.ExitObligedException;
import exceptions.NoSuchCommandException;

public class RequestHandler {
    private CommandManager commandManager;
    public RequestHandler(CommandManager commandManager){
        this.commandManager = commandManager;
    }
    public Response handle(Request request) {
        try {
            return commandManager.execute(request);
        } catch (IllegalArgumentException e) {
            return new Response(ResponseStatus.WRONG_ARGUMENTS,
                    "Неверное использование аргументов команды");
        } catch (CommandRuntimeException e) {
            return new Response(ResponseStatus.ERROR,
                    "Ошибка при исполнении программы");
        } catch (NoSuchCommandException e) {
            return new Response(ResponseStatus.ERROR, "Такой команды нет в списке");
        } catch (Exception e) {
            return new Response(ResponseStatus.EXIT);
        }
    }
}
