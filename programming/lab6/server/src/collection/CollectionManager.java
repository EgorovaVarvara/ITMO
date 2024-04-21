package collection;


import baseClasses.MusicBand;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 * The {@code CollectionManager} class managed the main collection and receive most of the commands.
 *
 * @author Egorova Varvara
 */
public class CollectionManager implements Serializable {
    static final Logger collectionManagerLogger = LogManager.getLogManager().getLogger(String.valueOf(CollectionManager.class));
    /**
     * Main collection of music bands.
     */
    private static HashSet<MusicBand> musicBands = new HashSet<>();
    /**
     * Date of initialization of collection.
     */
    private static LocalDateTime localDateTime = LocalDateTime.now();
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
     * Use to get the main collection.
     * @return HashSet of music bands
     */
    public static HashSet<MusicBand> getMusicBands() {
        return musicBands;
    }

    /**
     * Shows type of collection, its date of initialisation and amount of elements.
     */
    public static String info() {
        return "Тип коллекции: " + musicBands.getClass().getSimpleName() + "\nДата инициализации: " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\nКоличество элементов: " + musicBands.size();
    }

    /**
     * Shows all collection elements.
     */
    public static String show() {
        StringBuilder result = new StringBuilder();
        if (!musicBands.isEmpty()) {
            musicBands.forEach(musicBand -> result.append(musicBand.toString()));
            return result.toString();
        } else {
            return "Коллекция не содержит элементов.";
        }
    }


    /**
     * Add element to main collection.
     * @param musicBand that should be added to main collection
     */
    public static void add(MusicBand musicBand) {
        musicBands.add(musicBand);
    }

    /**
     * Updates element of collection by its id.
     * @param newMusicBand updated music band
     * @param id of music band
     */
    public static String updateId(MusicBand newMusicBand) {
        boolean flag = false;
        int id = newMusicBand.getId();
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getId() == id) {
                flag = true;
                musicBand.setName(newMusicBand.getName());
                musicBand.setCoordinates(newMusicBand.getCoordinates());
                musicBand.setNumberOfParticipants(newMusicBand.getNumberOfParticipants());
                musicBand.setMusicGenre(newMusicBand.getMusicGenre());
                musicBand.setLabel(newMusicBand.getLabel());
                break;
            }
        }
        if (!flag){
            return "Элемента с таким id нет в коллекции.";
        } else{
            return "Элемент успешно обновлен.";
        }
    }

    /**
     * Deletes element of collection by its id.
     * @param id of element that must be deleted
     */
    public static String removeById(int id) {
        boolean flag = false;
        for (MusicBand musicBand : musicBands){
            if (musicBand.getId() == id){
                flag = true;
                musicBands.remove(musicBand);
                break;
            }
        }
        if (!flag){
            return "Элемента с таким id нет в коллекции. ";
        } else {
            return "Элемент удален из коллекции. ";
        }
    }

    /**
     * Clears main collection.
     */
    public static void clear() {
        musicBands.clear();
    }

    /**
     * Saves collection to file.
     */
//    public void save() {
//        Parser parser = new Parser(this.filename);
//        try {
//            parser.saveToJson(musicBands);
//        } catch (NullPointerException e) {
//            System.out.println("Что-то пошло не так. ");
//        }
//    }



    /**
     * Adds element to collection if it max.
     * @param newMusicBand that can be added to collection
     */
    public static String addIfMax(MusicBand newMusicBand) {
        int maxNumberOfParticipants = 0;
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getNumberOfParticipants() > maxNumberOfParticipants) {
                maxNumberOfParticipants = musicBand.getNumberOfParticipants();
            }
        }
        if (newMusicBand.getNumberOfParticipants() > maxNumberOfParticipants) {
            add(newMusicBand);
            return "Элемент успешно добавлен в коллекцию. ";
        } else {
            return "Элемент не добавлен в коллекцию (не наибольший). ";
        }
    }

    /**
     * Adds element to collection if it min.
     * @param newMusicBand that can be added to collection
     */
    public static String addIfMin(MusicBand newMusicBand) {
        int minNumberOfParticipants = 2147483647;
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getNumberOfParticipants() < minNumberOfParticipants) {
                minNumberOfParticipants = musicBand.getNumberOfParticipants();
            }
        }
        if (newMusicBand.getNumberOfParticipants() < minNumberOfParticipants) {
            add(newMusicBand);
            return "Элемент успешно добавлен в коллекцию. ";
        } else {
            return "Элемент не добавлен в коллекцию (не наименьший). ";
        }
    }

    /**
     * Removes all elements in collection which are lower than given.
     * @param newMusicBand given music band
     */
    public static String removeLower(MusicBand newMusicBand) {
        musicBands.removeIf(musicBand -> musicBand.getNumberOfParticipants() < newMusicBand.getNumberOfParticipants());
        musicBands.add(newMusicBand);
        return "Элементы, меньшие чем заданный, удалены. ";
    }

    /**
     * Shows the sum of field {@code NumberOfParticipants}.
     */
    public static String sumOfNumberOfParticipants() {
        int sumOfNumberOfParticipants = 0;
        for (MusicBand musicBand : musicBands) {
            sumOfNumberOfParticipants += musicBand.getNumberOfParticipants();
        }
        return "Сумма значений поля numberOfParticipants: " + sumOfNumberOfParticipants;
    }

    /**
     * Shows all elements which {@code NumberOfParticipants} less than given.
     * @param newNumberOfParticipants given number of participants
     */
    public static String filterLessThanNumberOfParticipants(int newNumberOfParticipants) {
        StringBuilder result = new StringBuilder();
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getNumberOfParticipants() < newNumberOfParticipants) {
                result.append(musicBand);
            }
        }
        return result.toString();
    }

    /**
     * Shows all elements by descending order.
     */
    public static String printDescending() {
        ArrayList<MusicBand> musicBands1 = new ArrayList<>(musicBands);
        musicBands1.sort(Comparator.reverseOrder());
        String result = "";
        for (MusicBand musicBand : musicBands1) result += musicBand.toString();
        return result;
    }
    public static boolean isContainsId(int id){
        HashSet<Integer> ids = new HashSet<>();
        musicBands.forEach(musicBand -> ids.add(musicBand.getId()));
        return (ids.contains(id));
    }
}
