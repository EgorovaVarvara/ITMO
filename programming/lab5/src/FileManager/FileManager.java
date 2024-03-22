package FileManager;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

import java.util.HashSet;

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
    protected abstract void saveToJson(HashSet<MusicBand> musicBands) throws Exception;
    public abstract HashSet<MusicBand> loadFromJson() throws Exception;
}
