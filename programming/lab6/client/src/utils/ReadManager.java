package utils;

import baseClasses.MusicGenre;
import console.*;
import errors.FileModeException;

import java.util.Arrays;

/**
 * The {@code ReadManager} class manage reading different values from console
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
            name = scanner.nextLine().trim();
            if (name.isEmpty() || name.isBlank()) {
                console.printError("Имя не может быть пустой строкой/иными знаками, кроме букв");
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
            System.out.println("Введите координату X: ");
            String input = scanner.nextLine().trim();
            try{
                return Long.parseLong(input);
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
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
            System.out.println("Введите координату Y: ");
            String input = scanner.nextLine().trim();
            try{
                return Float.parseFloat(input);
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
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
            System.out.println("Введите число участников группы: ");
            String input = scanner.nextLine().trim();
            try{
                int numberOfParticipants = Integer.parseInt(input);
                if(numberOfParticipants > 0){
                    return numberOfParticipants;
                }else{
                    System.out.println("Число участников группы должно быть больше нуля. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    /**
     * @return MusicGenre for music band from console
     */
    @Override
    public MusicGenre readMusicGenre(){
        System.out.println("Вы должны ввести один из перечисленных музыкальных жанров: " + Arrays.toString(MusicGenre.values()));
        while (true){
            try{
                return MusicGenre.valueOf(console.getValidatedValue("\nВведите музыкальный жанр: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Жанр введён неверно. Введите ещё раз: ");
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
            System.out.println("Введите количество групп у лейбла: ");
            String input = scanner.nextLine().trim();
            try{
                long bands = Long.parseLong(input);
                if (bands > 0){
                    return bands;
                }else{
                    System.out.println("Число групп у лэйбла должно быть больше нуля. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }
}
