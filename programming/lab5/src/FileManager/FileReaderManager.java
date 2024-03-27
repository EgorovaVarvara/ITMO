package FileManager;

import BaseClasses.MusicGenre;
import Console.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The {@code FileReaderManager} class manage reading different values from files.
 */
public class FileReaderManager {
    private final Scanner scanner;

    /**
     * Constructor that creates object of {@code FileReaderManager} class.
     * @param filename
     * @throws FileNotFoundException if there is no such file
     */
    public FileReaderManager(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    /**
     * @return current scanner
     */
    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * @return name for music band from file
     * @throws IllegalArgumentException if string id empty
     */
    public String readName() throws IllegalArgumentException{
        String name = scanner.nextLine();
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("");
        } else {
            return name;
        }
    }

    /**
     * @return number of participants for music band from file
     * @throws IllegalArgumentException if number is not greater than zero
     */
    public int readNumberOfParticipants() throws IllegalArgumentException{
        int numberOfParticipants = scanner.nextInt();
        if (numberOfParticipants > 0) {
            return numberOfParticipants;
        } else {
            throw new IllegalArgumentException(""); // TODO message?
        }
    }

    /**
     * @return coordinate `x` for music band from file
     */
    public long readCoordinateX() {
        return scanner.nextLong();
    }

    /**
     * @return coordinate `y` for music band from file
     */
    public float readCoordinateY() {
        return scanner.nextFloat();
    }

    /**
     * @return MusicGenre for music band from file or null
     */
    public MusicGenre readMusicGenre() {
        String musicGenre = scanner.next().trim().toUpperCase(); // TODO а в остальных трим не надо тогда?...
        return switch (musicGenre) {
            case "PROGRESSIVE_ROCK" -> MusicGenre.PROGRESSIVE_ROCK;
            case "BLUES" -> MusicGenre.BLUES;
            case "POP" -> MusicGenre.POP;
            case "MATH_ROCK" -> MusicGenre.MATH_ROCK;
            case "POST_ROCK" -> MusicGenre.POST_ROCK;
            default -> null;
        };
    }

    /**
     * @return bands for music band from file
     * @throws IllegalArgumentException if number not greater than zero
     */
    public long readBands() throws IllegalArgumentException{
        long bands = scanner.nextLong();
        if (bands <= 0) {
            throw new IllegalArgumentException("");
        } else {
            return bands;
        }
    }

    /**
     * @return command and argument read from file
     * @throws IllegalArgumentException if line is empty or there is too many args
     */
    public String[] readCommandAndArgument() throws IllegalArgumentException{
        String[] commandAndArgument = scanner.nextLine().trim().toLowerCase().split(" ");
        String command = commandAndArgument[0].trim();
        if (CommandManager.getCommands().containsKey(command)) {
            if (command.equals("execute_script") || command.equals("filter_less_than_number_of_participants") || command.equals("remove_by_id") || command.equals("update")) {
                if (commandAndArgument.length == 2) {
                    return new String[]{command, commandAndArgument[1].trim()};
                } else throw new IllegalArgumentException("");
            } else {
                if (commandAndArgument.length == 1) {
                    return new String[]{command};
                } else throw new IllegalArgumentException("");
            }
        } else throw new IllegalArgumentException("");
    }
}
