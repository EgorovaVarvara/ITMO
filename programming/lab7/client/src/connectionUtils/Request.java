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
        this.args = command.getIntArgument() == null ? "" : command.getIntArgument().toString();
        this.musicBand = command.getMusicband();
    }
    public Request(ResponseStatus responseStatus, Command commandName, MusicBand musicBand){
        this.command = commandName;
    }
    public Request(Command commandName, String args){
        this.command = commandName;
        this.args = args.trim();
    }
    public Request(Command commandName, MusicBand musicBand){
        this.command = commandName;
        this.musicBand = musicBand;
    }
    public Request(Command commandName, String args, MusicBand musicBand){
        this.command = commandName;
        this.args = args.trim();
        this.musicBand = musicBand;
    }
    public boolean isEmpty(){
        return command == null && args.isEmpty() && musicBand == null;
    }
    public Command getCommandName(){
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
        return "Request[" + command.toString() +
                (args.isEmpty()
                        ? ""
                        : ", " + args) +
                ((musicBand == null)
                        ? "]"
                        : ", " + musicBand + "]");
    }
}
