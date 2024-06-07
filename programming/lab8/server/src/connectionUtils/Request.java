package connectionUtils;

import baseClasses.MusicBand;
import commands.Command;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 21L;
    private Command command;
    private String args = "";
    private MusicBand musicBand = null;
    private User user;
    public  Request(Command command){
        this.command = command;
        this.user = command.getUser();
        this.args = command.getIntArgument().toString();
        this.musicBand = command.getMusicband();
    }
    public boolean isEmpty(){
        return command == null && args.isEmpty() && musicBand == null;
    }
    public Command getCommand(){
        return command;
    }
    public String getArgs() {
        return args;
    }
    public MusicBand getMusicBand() {
        return musicBand;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Request request)) return false;
        return Objects.equals(command, request.command) && Objects.equals(args, request.args) && Objects.equals(musicBand, request.musicBand);
    }
    @Override
    public int hashCode(){
        return Objects.hash(command, args, musicBand);
    }
    @Override
    public String toString(){
     return "Request[" + command.getCommandName() +
             (args.isEmpty()
                ? ""
                : ", " + args) +
             ((musicBand == null)
                ? "]"
                : ", " + musicBand + "]");
    }
}
