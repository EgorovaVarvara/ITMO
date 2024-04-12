package connectionUtils;

import baseClasses.MusicBand;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 21L;
    private String commandName;
    private String args = "";
    private MusicBand musicBand = null;
    public Request(ResponseStatus responseStatus, String commandName, MusicBand musicBand){
        this.commandName = commandName.trim();
    }
    public Request(String commandName, String args){
        this.commandName = commandName.trim();
        this.args = args.trim();
    }
    public Request(String commandName, MusicBand musicBand){
        this.commandName = commandName.trim();
        this.musicBand = musicBand;
    }
    public Request(String commandName, String args, MusicBand musicBand){
        this.commandName = commandName.trim();
        this.args = args.trim();
        this.musicBand = musicBand;
    }
    public boolean isEmpty(){
        return commandName.isEmpty() && args.isEmpty() && musicBand == null;
    }
    public String getCommandName(){
        return commandName;
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
        return Objects.equals(commandName, request.commandName) && Objects.equals(args, request.args) && Objects.equals(musicBand, request.musicBand);
    }
    @Override
    public int hashCode(){
        return Objects.hash(commandName, args, musicBand);
    }
    @Override
    public String toString(){
     return "Request[" + commandName +
             (args.isEmpty()
                ? ""
                : ", " + args) +
             ((musicBand == null)
                ? "]"
                : ", " + musicBand + "]");
    }
}
