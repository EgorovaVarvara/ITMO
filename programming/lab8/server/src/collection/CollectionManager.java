package collection;


import baseClasses.MusicBand;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;


/**
 * The {@code CollectionManager} class managed the main collection and receive most of the commands.
 *
 * @author Egorova Varvara
 */
public class CollectionManager implements Serializable {
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
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Constructor that creates collection manager.
     */
    public CollectionManager() {
    }

    /**
     * Use to set the main collection.
     *
     * @param musicBands given music bands collection
     */
    public void setCollection(HashSet<MusicBand> musicBands) {
        this.musicBands = musicBands;
    }

    /**
     * Use to set filename of file with main collection.
     *
     * @param filename of file with main collection
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static LocalDateTime getLocalDateTime(){
        return localDateTime;
    }

    /**
     * Use to get the main collection.
     *
     * @return HashSet of music bands
     */
    public static HashSet<MusicBand> getCollection() {
        return musicBands;
    }

    /**
     * Shows type of collection, its date of initialisation and amount of elements.
     */
    public static String info() {
        lock.readLock().lock();
        String name = musicBands.getClass().getSimpleName();
        String date = localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        int size = musicBands.size();
        lock.readLock().unlock();
        return "Тип коллекции: " + name + "\nДата инициализации: " + date + "\nКоличество элементов: " + size;
    }

    /**
     * Shows all collection elements.
     */
    public static String show() {
        lock.readLock().lock();
        StringBuilder result = new StringBuilder();
        if (!musicBands.isEmpty()) {
            musicBands.forEach(musicBand -> result.append(musicBand.toString()));
            lock.readLock().unlock();
            return result.toString();
        } else {
            lock.readLock().unlock();
            return "Коллекция не содержит элементов.";
        }
    }


    /**
     * Add element to main collection.
     *
     * @param musicBand that should be added to main collection
     */
    public static void add(MusicBand musicBand) {
        lock.writeLock().lock();
        musicBands.add(musicBand);
        lock.writeLock().unlock();
    }

    /**
     * Updates element of collection by its id.
     *
     * @param newMusicBand updated music band
     */
    public static boolean updateId(MusicBand newMusicBand) {
        lock.writeLock().lock();
        boolean flag = musicBands.stream()
                .filter(band -> band.getId() == newMusicBand.getId())
                .findFirst()
                .map(band -> {
                    band.setName(newMusicBand.getName());
                    band.setCoordinates(newMusicBand.getCoordinates());
                    band.setNumberOfParticipants(newMusicBand.getNumberOfParticipants());
                    band.setMusicGenre(newMusicBand.getMusicGenre());
                    band.setLabel(newMusicBand.getLabel());
                    return band;
                })
                .isPresent();
        lock.writeLock().unlock();
        return flag;
    }

    /**
     * Deletes element of collection by its id.
     *
     * @param id id of element that must be deleted
     */
    public static String removeById(int id) {
        lock.writeLock().lock();
        boolean flag = false;
        for (MusicBand musicBand : musicBands){
            if (musicBand.getId() == id){
                flag = true;
                musicBands.remove(musicBand);
                break;
            }
        }
        lock.writeLock().unlock();
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
        lock.writeLock().lock();
        musicBands.clear();
        lock.writeLock().unlock();
    }


    /**
     * Adds element to collection if it max.
     *
     * @param newMusicBand that can be added to collection
     */
    public static String addIfMax(MusicBand newMusicBand) {
        lock.writeLock().lock();
        lock.readLock().lock();
        int maxNumberOfParticipants = musicBands.stream()
                .mapToInt(MusicBand::getNumberOfParticipants)
                .max()
                .getAsInt();
        lock.readLock().unlock();
        lock.writeLock().unlock();
        if (newMusicBand.getNumberOfParticipants() > maxNumberOfParticipants) {
            add(newMusicBand);
            return "Элемент успешно добавлен в коллекцию. ";
        } else {
            return "Элемент не добавлен в коллекцию (не наибольший). ";
        }
    }

    /**
     * Adds element to collection if it min.
     *
     * @param newMusicBand that can be added to collection
     */
    public static String addIfMin(MusicBand newMusicBand) {
        lock.writeLock().lock();
        lock.readLock().lock();
        int minNumberOfParticipants = musicBands.stream()
                .mapToInt(MusicBand::getNumberOfParticipants)
                .min()
                .getAsInt();
        lock.readLock().unlock();
        lock.writeLock().unlock();
        if (newMusicBand.getNumberOfParticipants() < minNumberOfParticipants) {
            add(newMusicBand);
            return "Элемент успешно добавлен в коллекцию. ";
        } else {
            return "Элемент не добавлен в коллекцию (не наименьший). ";
        }
    }

    /**
     * Removes all elements in collection which are lower than given.
     *
     * @param newMusicBand given music band
     */
    public static String removeLower(MusicBand newMusicBand) {
        lock.writeLock().lock();
        musicBands.removeIf(musicBand -> musicBand.getNumberOfParticipants() < newMusicBand.getNumberOfParticipants());
        musicBands.add(newMusicBand);
        lock.writeLock().unlock();
        return "Элементы, меньшие чем заданный, удалены. ";
    }

    /**
     * Shows the sum of field {@code NumberOfParticipants}.
     */
    public static String sumOfNumberOfParticipants() {
        lock.readLock().lock();
        int sumOfNumberOfParticipants = musicBands.stream()
                .mapToInt(MusicBand::getNumberOfParticipants)
                .sum();
        lock.readLock().unlock();
        return Integer.toString(sumOfNumberOfParticipants);
    }

    /**
     * Shows all elements which {@code NumberOfParticipants} less than given.
     *
     * @param newNumberOfParticipants given number of participants
     */
    public static HashSet<MusicBand> filterLessThanNumberOfParticipants(int newNumberOfParticipants) {
        lock.readLock().lock();
        HashSet<MusicBand> result = new HashSet<>();
        for (MusicBand musicBand : musicBands){
            if (musicBand.getNumberOfParticipants() < newNumberOfParticipants){
                result.add(musicBand);
            }
        }
        lock.readLock().unlock();
        return result;
    }

    /**
     * Shows all elements by descending order.
     */
    public static String printDescending() {
        lock.readLock().lock();
        String result = musicBands.stream()
                .sorted(Comparator.reverseOrder())
                .map(MusicBand::toString)
                .collect(Collectors.joining());
        lock.readLock().unlock();
        return result;
    }

    public static boolean isContainsId(int id) {
        HashSet<Integer> ids = new HashSet<>();
        musicBands.forEach(musicBand -> ids.add(musicBand.getId()));
        return (ids.contains(id));
    }
}
