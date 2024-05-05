package commands;

import collection.CollectionManager;
import connectionUtils.Response;
import connectionUtils.ResponseStatus;

import java.io.Serial;

public class ServiceCommand implements Command{
    @Serial
    private final static long serialVersionUID = 13L;
    private final String command;
    private String user_login;

    public ServiceCommand(String command) {
        this.command = command;
    }
    @Override
    public Response run() {
        String[] splitCommand = command.split(" ");
        String commandPart = splitCommand[0];
        String arg = splitCommand[1];
        return switch (commandPart) {
            case "check_id" -> {
                int id = Integer.parseInt(arg);
                boolean isPresented = CollectionManager.isContainsId(id);
                yield new Response(ResponseStatus.INFO, Boolean.toString(isPresented));
            }
            default -> new Response(ResponseStatus.INFO, "");
        };
    }

    @Override
    public String getCommandName() {
        return "service";
    }
}
