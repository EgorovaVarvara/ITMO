package Collection;

import BaseClasses.MusicBand;

import java.util.HashSet;

public class Validator {
    private final IdGenerator idGenerator = new IdGenerator();
    public Validator(){}
    public MusicBand getValidatedElement(MusicBand musicBand){
        if (musicBand.getId() < 0 || musicBand.getName() == null || musicBand.getName().isBlank() || musicBand.getCoordinates() == null || musicBand.getCoordinates().getX() == null || musicBand.getNumberOfParticipants() <= 0 || musicBand.getMusicGenre() == null || musicBand.getLabel() == null){
            return null;
        }else{
            if (musicBand.getId() == 0){
                musicBand.setId(idGenerator.generateId());
            }
            if (musicBand.getCreationDate() == null){
                musicBand.setCreationDate();
            }
            return musicBand;
        }
    }
    public IdGenerator getIdGenerator(){
        return idGenerator;
    }

}
