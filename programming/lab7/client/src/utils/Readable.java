package utils;

import baseClasses.MusicGenre;

public interface Readable {
    String readName();
    Long readCoordinateX();
    Float readCoordinateY();
    Integer readNumberOfParticipants();
    MusicGenre readMusicGenre();
    Long readBands();
    String readLogin();
    String readPassword();
}
