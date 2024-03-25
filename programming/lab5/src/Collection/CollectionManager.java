package Collection;


import BaseClasses.MusicBand;
import Console.*;
import FileManager.Parser;

import java.io.FileNotFoundException; // TODO never used 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * The {@code CollectionManager} class managed the main collection and receive most of the commands.
 *
 * @author Egorova Varvara
 */
public class CollectionManager {
    /**
     * Main collection of music bands.
     */
    HashSet<MusicBand> musicBands = new HashSet<>();
    /**
     * Date of initialization of collection.
     */
    private LocalDateTime localDateTime = LocalDateTime.now();
    /**
     * @see ClientManager
     */
    private ClientManager clientManager = new ClientManager();
    /**
     * Filename of file with main collection.
     */
    private String filename;

    /**
     * Constructor that creates collection manager.
     */
    public CollectionManager() {
    }

    /**
     * Use to set the main collection.
     * @param musicBands given music bands collection
     */
    public void setCollection(HashSet<MusicBand> musicBands) {
        this.musicBands = musicBands;
    }

    /**
     * Use to set filename of file with main collection.
     * @param filename of file with main collection
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Use to get client manager.
     * @return ClientManager
     */
    public ClientManager getClientManager() {
        return this.clientManager;
    }

    /**
     * Use to get the main collection.
     * @return HashSet of music bands
     */
    public HashSet<MusicBand> getMusicBands() {
        return musicBands;
    }

    /**
     * Shows type of collection, its date of initialisation and amount of elements.
     */
    public void info() {
        String info = "Тип коллекции: " + musicBands.getClass().getSimpleName() + "\nДата инициализации: " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\nКоличество элементов: " + musicBands.size();
        System.out.println(info);
    }

    /**
     * Shows all collection elements.
     */
    public void show() {
        if (!musicBands.isEmpty()) {
            for (MusicBand mb : musicBands) {
                System.out.println(mb.toString());
            }
        } else {
            System.out.println("Коллекция не содержит элементов.");
        }
    }

    /**
     * Shows all available commands.
     */
    public void help() {
        CommandManager.getCommands().values().forEach(command -> System.out.println(command.getDescription()));
    }

    /**
     * Add element to main collection.
     * @param musicBand that should be added to main collection
     */
    public void add(MusicBand musicBand) {
        musicBands.add(musicBand);
    }

    /**
     * Updates element of collection by its id.
     * @param newMusicBand updated music band
     * @param id of music band
     */
    public void updateId(MusicBand newMusicBand, int id) {
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getId() == id) {
                musicBand.setName(newMusicBand.getName());
                musicBand.setCoordinates(newMusicBand.getCoordinates());
                musicBand.setNumberOfParticipants(newMusicBand.getNumberOfParticipants());
                musicBand.setMusicGenre(newMusicBand.getMusicGenre());
                musicBand.setLabel(newMusicBand.getLabel());
                System.out.println("Элемент успешно обновлен.");
                break;
            }
        }

    }

    /**
     * Deletes element of collection by its id.
     * @param id of element that must be deleted
     */
    public void removeById(int id) {
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getId() == id) {
                musicBands.remove(musicBand);
                System.out.println("Элемент удален из коллекции. ");
                break;
            }
        }
    }

    /**
     * Clears main collection.
     */
    public void clear() {
        musicBands.clear();
    }

    /**
     * Saves collection to file.
     */
    public void save() {
        Parser parser = new Parser(this.filename);
        try {
            parser.saveToJson(this.musicBands);
        } catch (NullPointerException e) {
            System.out.println("Что-то пошло не так. ");
        }
    }

    /**
     * Finishes the work of program.
     */
    public void exit() {
        System.out.println("Работа завершена, до связи!");
        System.exit(0);
    }

    /**
     * Adds element to collection if it max.
     * @param newMusicBand that can be added to collection
     */
    public void addIfMax(MusicBand newMusicBand) {
        int maxNumberOfParticipants = 0;
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getNumberOfParticipants() > maxNumberOfParticipants) {
                maxNumberOfParticipants = musicBand.getNumberOfParticipants();
            }
        }
        if (newMusicBand.getNumberOfParticipants() > maxNumberOfParticipants) {
            add(newMusicBand);
            System.out.println("Элемент успешно добавлен в коллекцию. ");
        } else {
            System.out.println("Элемент не добавлен в коллекцию (не наибольший). ");
        }
    }

    /**
     * Adds element to collection if it min.
     * @param newMusicBand that can be added to collection
     */
    public void addIfMin(MusicBand newMusicBand) {
        int minNumberOfParticipants = 2147483647;
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getNumberOfParticipants() < minNumberOfParticipants) {
                minNumberOfParticipants = musicBand.getNumberOfParticipants();
            }
        }
        if (newMusicBand.getNumberOfParticipants() < minNumberOfParticipants) {
            add(newMusicBand);
            System.out.println("Элемент успешно добавлен в коллекцию. ");
        } else {
            System.out.println("Элемент не добавлен в коллекцию (не наименьший). ");
        }
    }

    /**
     * Removes all elements in collection which are lower than given.
     * @param newMusicBand given music band
     */
    public void removeLower(MusicBand newMusicBand) {
        musicBands.removeIf(musicBand -> musicBand.getNumberOfParticipants() < newMusicBand.getNumberOfParticipants());
        musicBands.add(newMusicBand);
        System.out.println("Элементы, меньшие чем заданный, удалены. ");
    }

    /**
     * Shows the sum of field {@code NumberOfParticipants}.
     */
    public void sumOfNumberOfParticipants() {
        int sumOfNumberOfParticipants = 0;
        for (MusicBand musicBand : musicBands) {
            sumOfNumberOfParticipants += musicBand.getNumberOfParticipants();
        }
        System.out.println("Сумма значений поля numberOfParticipants: " + sumOfNumberOfParticipants);
    }

    /**
     * Shows all elements which {@code NumberOfParticipants} less than given.
     * @param newNumberOfParticipants given number of participants
     */
    public void filterLessThanNumberOfParticipants(int newNumberOfParticipants) {
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getNumberOfParticipants() < newNumberOfParticipants) {
                System.out.println(musicBand);
            }
        }
    }

    /**
     * Shows all elements by descending order.
     */
    public void printDescending() {
        ArrayList<MusicBand> musicBands1 = new ArrayList<>(musicBands);
        musicBands1.sort(Comparator.reverseOrder());
        for (MusicBand musicBand : musicBands1) {
            System.out.println(musicBand);
        }
    }
}
