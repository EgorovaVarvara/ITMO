package Collection;

import BaseClasses.MusicBand;

import java.util.HashSet;

public class IdGenerator {
    private HashSet<Integer> generatedIds = new HashSet<>();
    public int generateId(){
        int id = (int) System.currentTimeMillis();
        while (generatedIds.contains(id)){
            id = (int) System.currentTimeMillis();
        }
        generatedIds.add(id);
        return id;
    }
    public void addId(MusicBand musicBand){
        generatedIds.add(musicBand.getId());
    }
    public HashSet<Integer> getGeneratedIds(){
        return this.generatedIds;
    }
}
