package utils;

import Commands.Command;
import Commands.ServiceCommand;
import baseClasses.CommandType;
import connectionUtils.Request;

public class CollectionValidator {
    private static Request request;
    public static void setRequest(Request request){
        CollectionValidator.request = request;
    }
    public static Boolean checkIfExists(CommandType type, int id) throws Exception{
        request.send(CommandSerializer.serialize(new Command(CommandType.SERVICE, "check_id %d".formatted(id))));
        Boolean receiveStatus = Boolean.parseBoolean(request.receive());
        if (type.equals(CommandType.ADD) || type.equals(CommandType.ADD_IF_MAX) || type.equals(CommandType.ADD_IF_MIN) || type.equals(CommandType.REMOVE_LOWER)){
            return receiveStatus;
        } else if (type.equals(CommandType.UPDATE) || type.equals(CommandType.REMOVE_BY_ID)){
            return !receiveStatus;
        }
        return false;
    }
    public static Boolean isMusicBandValid(CommandType commandType, String[] args){
        if (args.length < 1){
            System.err.println("Недостаточно аргументов для команды " + commandType.name());
            return null;
        }
        int id;
        try{
            id = Integer.parseInt(args[0]);
            if (checkIfExists(commandType, id)){
                System.err.println("Id " + id + " не подходит для команды " + commandType.name());
                return false;
            }
        } catch (Exception e) {
            System.err.println("Неверный аргумент для команды " + commandType.name());
            return false;
        }
        return true;
    }
}
