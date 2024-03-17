package Collection;

import Commands.*;
import BaseClasses.MusicBand;
import Console.ClientManager;
import Console.CommandManager;
import FileManager.Parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    HashSet<MusicBand> musicBands = new HashSet<>();
    private LocalDateTime localDateTime = LocalDateTime.now();
    private ClientManager clientManager = new ClientManager();
    private String filename;

    public CollectionManager() {
    }

    public void setCollection(HashSet<MusicBand> musicBands) {
        this.musicBands = musicBands;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ClientManager getClientManager() {
        return this.clientManager;
    }

    public HashSet<MusicBand> getMusicBands() {
        return musicBands;
    }

    public void info() {
        String info = "Тип коллекции: " + musicBands.getClass().getSimpleName() + "\nДата инициализации: " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\nКоличество элементов: " + musicBands.size();
        System.out.println(info);
    }

    public void show() {
        if (!musicBands.isEmpty()) {
            for (MusicBand mb : musicBands) {
                System.out.println(mb.toString());
            }
        } else {
            System.out.println("Коллекция не содержит элементов.");
        }
    }

    public void help() {
        CommandManager commandManager = new CommandManager(new CollectionManager());
        HashMap<String, Command> commands = commandManager.getCommands();
        commands.values().forEach(command -> System.out.println(command.getDescription()));
    }

    public void add(MusicBand musicBand) {
        musicBands.add(musicBand);
    }

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

    public void removeById(int id) {
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getId() == id) {
                musicBands.remove(musicBand);
                System.out.println("Элемент удален из коллекции. ");
                break;
            }
        }
    }

    public void clear() {
        musicBands.clear();
    }

    public void save() {
        Parser parser = new Parser();
        try {
            parser.saveToJson(this.filename, this.musicBands);
            System.out.println("Коллекция сохранена в файл. ");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так. ");
        }
    }

    public void exit() {
        System.out.println("Работа завершена, до связи!");
        System.exit(0);
    }

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

    public void removeLower(MusicBand newMusicBand) {
        musicBands.removeIf(musicBand -> musicBand.getNumberOfParticipants() < newMusicBand.getNumberOfParticipants());
        musicBands.add(newMusicBand);
        System.out.println("Элементы, меньшие чем заданный, удалены. ");
    }

    public void sumOfNumberOfParticipants() {
        int sumOfNumberOfParticipants = 0;
        for (MusicBand musicBand : musicBands) {
            sumOfNumberOfParticipants += musicBand.getNumberOfParticipants();
        }
        System.out.println("Сумма значений поля numberOfParticipants: " + sumOfNumberOfParticipants);
    }

    public void filterLessThanNumberOfParticipants(int newNumberOfParticipants) {
        for (MusicBand musicBand : musicBands) {
            if (musicBand.getNumberOfParticipants() < newNumberOfParticipants) {
                System.out.println(musicBand);
            }
        }
    }

    public void printDescending() {
        ArrayList<MusicBand> musicBands1 = new ArrayList<>(musicBands);
        musicBands1.sort(Comparator.reverseOrder());
        for (MusicBand musicBand : musicBands1) {
            System.out.println(musicBand);
        }
    }
}
