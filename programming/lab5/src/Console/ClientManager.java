package Console;

import BaseClasses.Coordinates;
import BaseClasses.Label;
import BaseClasses.MusicBand;
import BaseClasses.MusicGenre;

public class ClientManager {
    ReadManager readManager = new ReadManager();
    public MusicBand getMusicBand(){
        String name = readManager.readName();
        Long x = readManager.readCoordinateX();
        float y = readManager.readCoordinateY();
        int numberOfParticipants = readManager.readNumberOfParticipants();
        MusicGenre musicGenre = readManager.readMusicGenre();
        long bands = readManager.readBands();
        return new MusicBand(name, new Coordinates(x, y), numberOfParticipants, musicGenre, new Label(bands));
    }
}
