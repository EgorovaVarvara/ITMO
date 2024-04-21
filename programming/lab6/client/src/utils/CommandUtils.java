package utils;

import baseClasses.CommandType;

public class CommandUtils {
    public static CommandType getCommandType(String message){
        try {
            return CommandType.valueOf(message.toUpperCase());
        } catch (IllegalArgumentException e){
            return CommandType.DEFAULT;
        }
    }
}
