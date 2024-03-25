package Console;

import BaseClasses.MusicGenre;

import java.util.Arrays;

/**
 * The {@code ReadManager} class manage reading different values from console
 */
public class ReadManager {
    /**
     * @see ConsoleManager
     */
    ConsoleManager consoleManager = new ConsoleManager(); // TODO модификатор доступа?

    /**
     * @return name for music band from console
     */
    public String readName(){
        System.out.println("Введите название музыкальной группы: ");
        String name = consoleManager.readLine();
        while (true){
            if (name.isEmpty() || name.isBlank()){
                System.out.println("Имя не может быть пустой строкой. Введите еще раз: ");
                name = consoleManager.readLine();
            }else{
                return name;
            }
        }
    }

    /**
     * @return coordinate `x` for music band from console
     */
    public Long readCoordinateX(){
        System.out.println("Введите координату X: ");
        while (true){
            try{
                return consoleManager.readLong();
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
            }
        }
    }

    /**
     * @return coordinate `y` for music band from console
     */
    public float readCoordinateY(){
        System.out.println("Введите координату Y: ");
        while (true){
            try{
                return consoleManager.readFloat();
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
            }
        }
    }

    /**
     * @return number of participants for music band from console
     */
    public int readNumberOfParticipants(){
        System.out.println("Введите число участников группы: ");
        while (true){
            try{
                int numberOfParticipants = consoleManager.readInt();
                if(numberOfParticipants > 0){
                    return numberOfParticipants;
                }else{
                    System.out.println("Число участников группы должно быть больше нуля. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
            }
        }
    }

    /**
     * @return MusicGenre for music band from console
     */
    public MusicGenre readMusicGenre(){
        System.out.println("Вы должны ввести один из перечисленных музыкальных жанров: " + Arrays.toString(MusicGenre.values()));
        while (true){
            try{
                return MusicGenre.valueOf(consoleManager.getValidatedValue("\nВведите музыкальный жанр: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Жанр введён неверно. Введите ещё раз: ");
            }
        }
    }

    /**
     * @return bands for music band from console
     */
    public long readBands(){
        System.out.println("Введите количество групп у лейбла: ");
        while (true){
            try{
                long bands = consoleManager.readLong();
                if (bands > 0){
                    return bands;
                }else{
                    System.out.println("Число групп у лэйбла должно быть больше нуля. Введите еще раз: ");
                }
            }catch (NumberFormatException e){
                System.out.println("Число введено неверно. Введите ещё раз: ");
            }
        }
    }
}
