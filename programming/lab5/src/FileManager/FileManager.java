package FileManager;

import BaseClasses.MusicBand;
import Collection.CollectionManager;

import java.util.HashSet;

public abstract class FileManager {
    private String fileName;
    public FileManager(){
    }
    public String getFileName(){
        return this.fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    protected abstract void saveToJson(String fileName, HashSet<MusicBand> musicBands) throws Exception;
    public abstract HashSet<MusicBand> loadFromJson(String fileName) throws Exception;
}
