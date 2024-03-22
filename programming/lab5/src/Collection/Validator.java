package Collection;

import BaseClasses.MusicBand;

import java.util.HashSet;

/**
 * The {@code Validator} class use to validate music band.
 *
 * @author Egorova Varvara
 */
public class Validator {
    /**
     * @see IdGenerator
     */
    private final IdGenerator idGenerator = new IdGenerator();

    /**
     * Constructor that creates validator.
     */
    public Validator(){}

    /**
     * Validates given music band.
     * @param musicBand given music band
     * @return validated music band or null
     */
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

    /**
     * @return idGenerator
     */
    public IdGenerator getIdGenerator(){
        return idGenerator;
    }

}
