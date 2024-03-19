package Console;

import BaseClasses.MusicGenre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderManager {
    private final Scanner scanner;

    public FileReaderManager(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public String readName() {
        String name = scanner.nextLine();
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("");
        } else {
            return name;
        }
    }

    public int readNumberOfParticipants() {
        int numberOfParticipants = scanner.nextInt();
        if (numberOfParticipants > 0) {
            return numberOfParticipants;
        } else {
            throw new IllegalArgumentException("");
        }
    }

    public long readCoordinateX() {
        return scanner.nextLong();
    }

    public float readCoordinateY() {
        return scanner.nextFloat();
    }

    public String readMusicGenre() {
        return scanner.nextLine();
    }

    public long readBands() {
        long bands = scanner.nextLong();
        if (bands <= 0) {
            throw new IllegalArgumentException("");
        } else {
            return bands;
        }
    }

    public String[] readCommandAndArgument() {
        String[] commandAndArgument = scanner.nextLine().trim().split(" ");
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
