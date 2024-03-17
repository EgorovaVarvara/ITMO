package Console;

import BaseClasses.MusicGenre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderManager{
    private String filename;
    private File file = new File(filename);
    private Scanner scanner = new Scanner(file);
    public FileReaderManager(String filename) throws FileNotFoundException {
        this.filename = filename;
    }
    public String readName(){
        String name = scanner.nextLine();
        if (name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("");
        }else{
            return name;
        }
    }
    public int readNumberOfParticipants() {
        int numberOfParticipants = scanner.nextInt();
        if (numberOfParticipants > 0){
            return numberOfParticipants;
        }else{
            throw new IllegalArgumentException("");
        }
    }

    public long readCoordinateX() {
        return scanner.nextLong();
    }

    public float readCoordinateY() {
        float y = scanner.nextFloat();
        return y;
    }
    public MusicGenre readMusicGenre(){
        return MusicGenre.valueOf(scanner.nextLine());
    }
    public long readBands(){
        long bands = scanner.nextLong();
        if (bands <= 0){
            throw new IllegalArgumentException("");
        }else{
            return bands;
        }
    }
}
