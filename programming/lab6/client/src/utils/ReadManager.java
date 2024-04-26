package utils;

import baseClasses.MusicGenre;
import console.*;
import errors.FileModeException;

import java.util.Arrays;

/**
 * The {@code ReadManager} class manage reading different fields of MusicBand class from console
 * @see utils.Readable
 * @author Egorova Varvara
 */
public class ReadManager implements Readable{
    private final ReaderWriter console;
    private final UserInput scanner;
    public ReadManager(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    /**
     * @return name for music band from console
     */
    @Override
    public String readName(){
        String name;
        while (true) {
            console.write("Введите название группы:");
            name = console.readLine();
            if (name.isEmpty() || name.isBlank()) {
                console.printError("Имя не может быть пустой строкой");
                if (Console.isFileMode()) throw new FileModeException();
            } else {
                return name;
            }
        }
    }

    /**
     * @return coordinate `x` for music band from console
     */
    @Override
    public Long readCoordinateX(){
        while (true){
            console.write("Введите координату X: ");
            try{
                return console.readLong();
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return coordinate `y` for music band from console
     */
    @Override
    public Float readCoordinateY(){
        while (true){
            console.write("Введите координату Y: ");
            try{
                return console.readFloat();
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return number of participants for music band from console
     */

    @Override
    public Integer readNumberOfParticipants(){
        while (true){
            console.write("Введите число участников группы: ");
            try{
                int numberOfParticipants = console.readInt();
                if(numberOfParticipants > 0){
                    return numberOfParticipants;
                }else{
                    console.printError("Число участников группы должно быть больше нуля. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return MusicGenre for music band from console
     */
    @Override
    public MusicGenre readMusicGenre(){
        console.write("Вы должны ввести один из перечисленных музыкальных жанров: " + Arrays.toString(MusicGenre.values()));
        while (true){
            try{
                return MusicGenre.valueOf(console.getValidatedValue("\nВведите музыкальный жанр: ").toUpperCase());
            }catch (IllegalArgumentException e){
                console.printError("Жанр введён неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return bands for music band from console
     */

    @Override
    public Long readBands(){
        while (true){
            console.write("Введите количество групп у лейбла: ");
            try{
                long bands = console.readLong();
                if (bands > 0){
                    return bands;
                }else{
                    System.out.println("Число групп у лэйбла должно быть больше нуля. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                console.printError("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }
}
