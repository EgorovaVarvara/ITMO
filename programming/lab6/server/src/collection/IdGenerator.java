package collection;

import baseClasses.MusicBand;
import utils.Server;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;

import static java.lang.Math.abs;

/**
 * The {@code IdGenerator} class generates unique ids to music bands.
 *
 * @author Egorova Varvara
 */
public class IdGenerator implements Serializable {
    @Serial
    private static final long serialVersionUID = 1020L;
    /**
     * HashSet with used ids.
     */
    private static HashSet<Integer> generatedIds = new HashSet<>();

    /**
     * Generates unique id by using {@code System.currentTimeMillis}.
     * @return unique id
     */
    public static int generateId(){
        int id = abs((int) System.currentTimeMillis());
        while (generatedIds.contains(id)){
            id = (int) System.currentTimeMillis();
        }
        generatedIds.add(id);
        return id;
    }

    /**
     * Add id to used ids.
     * @param musicBand that id should add to used ids
     */
    public void addId(MusicBand musicBand){
        generatedIds.add(musicBand.getId());
    }

    /**
     * Use to get used ids.
     * @return used ids
     */
    public HashSet<Integer> getGeneratedIds(){
        return this.generatedIds;
    }
}
