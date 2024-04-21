package utils;

import commands.*;
import baseClasses.CommandType;
import baseClasses.MusicBand;
import console.Console;
import errors.InvalidFormException;
import forms.MusicBandForm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandFactory {
    public static Command createCommand(CommandType type, String[] userInput){
        String[] args;
        if (userInput.length == 0){
            args = new String[]{};
        } else {
            args = Arrays.copyOfRange(userInput, 1, userInput.length);
        }
        return switch (type){
            case EXIT -> {
                System.out.println("Отключаемся...");
                System.exit(0);
                yield null;
            }
            case EXECUTE_SCRIPT -> {
                if (args.length < 1){
                    System.err.println("Недостаточно аргументов для команды " + CommandType.EXECUTE_SCRIPT);
                    yield null;
                }
                String filePath = args[0];
                if (!FileUtil.isFileExist(filePath)) {
                    System.err.println("Файл не найден: " + filePath);
                    yield null;
                }
                ArrayList<Command> commands = new ScriptExecutor(new File(filePath)).readScript().getCommandList();
                yield new ExecuteScriptCommand(commands);
            }
            case CLEAR -> new ClearCommand();
            case HELP -> new HelpCommand();
            case INFO -> new InfoCommand();
            case PRINT_DESCENDING -> new PrintDescendingCommand();
            case SHOW -> new ShowCommand();
            case SUM_OF_NUMBER_OF_PARTICIPANTS -> new SumOfNumberOfParticipantsCommand();
            case REMOVE_BY_ID -> {
                if (args.length < 1) {
                    System.err.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    yield new RemoveByIdCommand(Integer.parseInt(args[0]));
                } catch (NumberFormatException e) {
                    System.err.println("Аргумент должен быть целым числом. ");
                    yield null;
                }
            }
            case FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS -> {
                if (args.length < 1) {
                    System.err.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    yield new FilterLessThanNumberOfParticipantsCommand(Integer.parseInt(args[0]));
                } catch (NumberFormatException e) {
                    System.err.println("Число учатников группы должно быть целым числом. ");
                    yield null;
                }
            }
            case UPDATE -> {
                if (args.length < 1){
                    System.err.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    int id = Integer.parseInt(args[0]);
                    MusicBand musicBand;
                    MusicBandForm musicBandForm = new MusicBandForm(new Console());
                    musicBand = musicBandForm.build();
                    musicBand.setId(id);
                    if (musicBand != null) yield new UpdateIdCommand(musicBand);
                    yield null;
                } catch (InvalidFormException | NumberFormatException e){
                    System.out.println("id должен быть целым положительным числом.");
                    yield null;
                }
            }
            case ADD -> {
                try {
                    MusicBand musicBand;
                    MusicBandForm musicBandForm = new MusicBandForm(new Console());
                    musicBand = musicBandForm.build();
                    yield new AddCommand(musicBand);
                } catch (InvalidFormException e){
                    yield null;
                }
            }
            case ADD_IF_MAX -> {
                try {
                    MusicBand musicBand;
                    MusicBandForm musicBandForm = new MusicBandForm(new Console());
                    musicBand = musicBandForm.build();
                    yield new AddIfMaxCommand(musicBand);
                } catch (InvalidFormException e){
                    yield null;
                }
            }
            case ADD_IF_MIN ->{
                try {
                    MusicBand musicBand;
                    MusicBandForm musicBandForm = new MusicBandForm(new Console());
                    musicBand = musicBandForm.build();
                    yield new AddIfMinCommand(musicBand);
                } catch (InvalidFormException e){
                    yield null;
                }
            }
            case REMOVE_LOWER -> {
                try{
                    MusicBand musicBand;
                    MusicBandForm musicBandForm = new MusicBandForm(new Console());
                    musicBand = musicBandForm.build();
                    if (musicBand != null) yield new RemoveLowerCommand(musicBand);
                    yield null;
                } catch (InvalidFormException e){
                    yield null;
                }
            }
            default -> {
                System.out.println("Неизвестная команда: " + userInput[0]);
                yield null;
            }
        };
    }
}
