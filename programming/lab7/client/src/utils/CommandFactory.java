package utils;

import commands.*;
import baseClasses.CommandType;
import baseClasses.MusicBand;
import connectionUtils.User;
import console.Console;
import forms.MusicBandForm;
import forms.UserForm;

import java.util.Arrays;

/**
 * Class {@code CommandFactory} contains one method to create object of concrete command class
 * @author Egorova Varvara
 */
public class CommandFactory {
    /**
     * Method that creates command depending on user input
     * @param type Command type of users command
     * @param userInput String array of users input
     * @return Object of command
     * @see Command
     * @see CommandType
     */
    public static Command createCommand(CommandType type, String[] userInput) {
        String[] args;
        if (userInput.length == 0) {
            args = new String[]{};
        } else {
            args = Arrays.copyOfRange(userInput, 1, userInput.length);
        }
        return switch (type) {
            case EXIT -> {
                System.out.println("Отключаемся...");
                System.exit(0);
                yield null;
            }
            case CLEAR -> new ClearCommand();
            case HELP -> new HelpCommand();
            case INFO -> new InfoCommand();
            case PRINT_DESCENDING -> new PrintDescendingCommand();
            case SHOW -> new ShowCommand();
            case SUM_OF_NUMBER_OF_PARTICIPANTS -> new SumOfNumberOfParticipantsCommand();
            case REMOVE_BY_ID -> {
                if (args.length < 1) {
                    System.out.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    yield new RemoveByIdCommand(Integer.parseInt(args[0]));
                } catch (NumberFormatException e) {
                    System.out.println("Аргумент должен быть целым числом. ");
                    yield null;
                }
            }
            case FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS -> {
                if (args.length < 1) {
                    System.out.println("Недостаточно аргументов для команды " + type.name());
                    yield null;
                }
                try {
                    yield new FilterLessThanNumberOfParticipantsCommand(Integer.parseInt(args[0]));
                } catch (NumberFormatException e) {
                    System.out.println("Число учатников группы должно быть целым числом. ");
                    yield null;
                }
            }
            case UPDATE -> {
                if (args.length < 1) {
                    System.out.println("Недостаточно аргументов для команды " + type.name());
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
                } catch (NumberFormatException e) {
                    System.out.println("id должен быть целым положительным числом.");
                    yield null;
                }
            }
            case ADD -> {
                MusicBand musicBand;
                MusicBandForm musicBandForm = new MusicBandForm(new Console());
                musicBand = musicBandForm.build();
                yield new AddCommand(musicBand);
            }
            case ADD_IF_MAX -> {
                MusicBand musicBand;
                MusicBandForm musicBandForm = new MusicBandForm(new Console());
                musicBand = musicBandForm.build();
                yield new AddIfMaxCommand(musicBand);
            }
            case ADD_IF_MIN -> {
                MusicBand musicBand;
                MusicBandForm musicBandForm = new MusicBandForm(new Console());
                musicBand = musicBandForm.build();
                yield new AddIfMinCommand(musicBand);

            }
            case REMOVE_LOWER -> {
                MusicBand musicBand;
                MusicBandForm musicBandForm = new MusicBandForm(new Console());
                musicBand = musicBandForm.build();
                if (musicBand != null) yield new RemoveLowerCommand(musicBand);
                yield null;
            }
            case REGISTRATION -> {
                User user;
                UserForm userForm = new UserForm(new Console());
                user = userForm.build();
                if (user != null) yield new RegistrationCommand(user);
                yield null;
            }
            default -> {
                System.out.println("Неизвестная команда: " + userInput[0]);
                yield null;
            }
        };
    }
}
