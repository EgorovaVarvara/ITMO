package fileManager;

import baseClasses.MusicBand;

import java.util.HashSet;

/**
 * The {@code FileManager} abstract class manage work with files
 *
 * @author Egorova Varvara
 */
public abstract class FileManager {
    private String fileName;
    public FileManager(){
    }
    public FileManager(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return this.fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void saveToJson() throws Exception {
    }

    public abstract HashSet<MusicBand> loadFromJson() throws Exception;
}
