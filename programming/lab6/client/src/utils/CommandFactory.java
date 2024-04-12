package utils;

import Commands.Command;
import baseClasses.CommandType;
import baseClasses.MusicBand;
import console.Console;
import errors.InvalidFormException;
import forms.MusicBandForm;

import java.io.File;
import java.util.ArrayList;

public class CommandFactory {
    public static Command createCommand(CommandType type, String[] args){
        return switch (type){
            case EXIT -> {
                System.out.println("Отключаемся...");
                System.exit(0);
                yield null;
            }
            case EXECUTE_SCRIPT -> {
                if (args.length < 1){
                    System.err.println("Not enough arguments for command " + CommandType.EXECUTE_SCRIPT);
                    yield null;
                }
                String filePath = args[0];
                if (!FileUtil.isFileExist(filePath)) {
                    System.err.println("Script file does not exist: " + filePath);
                    yield null;
                }
                ArrayList<Command> commands = new ScriptExecutor(new File(filePath)).readScript().getCommandList();
                yield new Command(type, commands);
            }
            case CLEAR, HELP, INFO, PRINT_DESCENDING, SHOW, SUM_OF_NUMBER_OF_PARTICIPANTS -> new Command(type);
            case REMOVE_BY_ID, FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS -> {
                if (args.length < 1) {
                    System.err.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    yield new Command(type, Integer.parseInt(args[0]));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid argument for command " + type.name());
                    yield null;
                }
            }
            case UPDATE -> {
                if (args.length < 1){
                    System.err.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    MusicBand musicBand = null;
                    MusicBandForm musicBandForm = new MusicBandForm(new Console());
                    musicBand = musicBandForm.build();
                    if (musicBand != null) yield new Command(type, musicBand);
                    yield null;
                } catch (InvalidFormException e){
                    yield null;
                }
            }
            case ADD, ADD_IF_MAX, ADD_IF_MIN, REMOVE_LOWER -> {
                try{
                    MusicBand musicBand = null;
                    MusicBandForm musicBandForm = new MusicBandForm(new Console());
                    musicBand = musicBandForm.build();
                    if (musicBand != null) yield new Command(type, musicBand);
                    yield null;
                } catch (InvalidFormException e){
                    yield null;
                }
            }
            default -> {
                System.err.println("Неизвестная команда: " + type.name());
                yield null;
            }
        };
    }
}
