package BaseClasses;

import Collection.IdGenerator;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * The {@code MusicBand} class represents a music band, which includes its name, coordinates, creation date, number of participants, music genre and label.
 *
 * @author Egorova Varvara
 */
public class MusicBand implements Comparable<MusicBand>, Serializable {
    /**
     * The unique id of the music band.
     * Greater than zero, generates automatically, cant be null.
     */
    private int id;
    /**
     * The name of the music band.
     * Cant be null or empty.
     */
    private String name;
    /**
     * The coordinates of music band.
     * Cant be null.
     */
    private Coordinates coordinates;
    /**
     * The date of creation of music band.
     * Generates automatically, cant be null.
     */
    private java.time.LocalDateTime creationDate = java.time.LocalDateTime.now();
    /**
     * The number of participants of the music band.
     * Greater than zero.
     */
    private int numberOfParticipants;
    /**
     * The music genre of the music band.
     * Cant be null.
     */
    private MusicGenre musicGenre;
    /**
     * The label of the music band.
     * Cant be null.
     */
    private Label label;
    IdGenerator idGenerator = new IdGenerator();

    /**
     * Constructor to create music band with given values.
     * Automatically sets id and creation date.
     *
     * @param name the name of the music band
     * @param coordinates the coordinates of the music band
     * @param numberOfParticipants the number of participants of the music band
     * @param musicGenre the music genre of music band
     * @param label the label of the music band
     *
     * @see Coordinates
     * @see MusicGenre
     * @see Label
     *
     * @throws IllegalArgumentException if some fields are null or empty
     */
    public MusicBand(String name, Coordinates coordinates, int numberOfParticipants, MusicGenre musicGenre, Label label){
        if (name == null || name.isEmpty() || coordinates == null || numberOfParticipants <= 0 || musicGenre == null || label == null){
            throw new IllegalArgumentException("The fields can't be null or empty sequences.");
        }else{
            this.id = idGenerator.generateId();
            this.name = name;
            this.coordinates = coordinates;
            this.numberOfParticipants = numberOfParticipants;
            this.musicGenre = musicGenre;
            this.label = label;
        }
    }

    /**
     * Use to get the id of the movie.
     * @return id of music band
     */
    public int getId(){
        return this.id;
    }

    /**
     * Use to set the id of the music band.
     * @param id given id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Use to get the name of the music band.
     * @return name of the music band
     */
    public String getName(){
        return this.name;
    }

    /**
     * Use to set the name of the music band.
     * @param name given name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Use to get the coordinates of the music band.
     * @return coordinates of the music band
     */
    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    /**
     * Use to set the coordinates of the music band.
     * @param coordinates given coordinates
     */
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    /**
     * Use to get the creation date of music band.
     * @return the creation date of music band
     */
    public java.time.LocalDateTime getCreationDate(){
        return this.creationDate;
    }

    /**
     * Use to generate creation date of music band.
     */
    public void setCreationDate(){
        this.creationDate = java.time.LocalDateTime.now();
    }

    /**
     * Use to get number of participants of music band.
     * @return number of participants of music band
     */
    public int getNumberOfParticipants(){
        return this.numberOfParticipants;
    }

    /**
     * Use to set number of participants of music band.
     * @param numberOfParticipants given number of participants
     */
    public void setNumberOfParticipants(int numberOfParticipants){
        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * Use to get music genre of music band.
     * @return music genre of music band
     */
    public MusicGenre getMusicGenre(){
        return this.musicGenre;
    }

    /**
     * Use to set music genre od music band.
     * @param musicGenre given music genre
     */
    public void setMusicGenre(MusicGenre musicGenre){
        this.musicGenre = musicGenre;
    }

    /**
     * Use to get label of music band.
     * @return label of music band
     */
    public Label getLabel(){
        return this.label;
    }

    /**
     * Use to set label of music band.
     * @param label given label
     */
    public void setLabel(Label label){
        this.label = label;
    }

    /**
     * Use to compare music bands.
     * @param musicBand the music band to be compared.
     */
    @Override
    public int compareTo(MusicBand musicBand) {
        if (this.numberOfParticipants > musicBand.getNumberOfParticipants()){
            return 1;
        }else if (this.numberOfParticipants < musicBand.numberOfParticipants){
            return -1;
        }else{
            return 0;
        }
    }

    /**
     * Use to check if two music bands are the same.
     * @param musicBand the music band to be compared.
     * @return boolean
     */
    public boolean equals(MusicBand musicBand){
        return (this.id == musicBand.getId()) || (this.name.equals(musicBand.getName()) && this.coordinates.equals(musicBand.getCoordinates()) && this.numberOfParticipants == musicBand.getNumberOfParticipants() && this.musicGenre.equals(musicBand.getMusicGenre()) && this.label.equals(musicBand.getLabel()));
    }
    @Override
    public String toString(){
        return "\n" + this.id + ": "
                + "Band's name: " + this.name + "\n"
                + "Band's coordinates: " + this.coordinates + "\n"
                + "Date of creation: " + this.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n"
                + "Number of participants: " + this.numberOfParticipants + "\n"
                + "Genre of music: " + this.musicGenre + "\n"
                + "Label: " + this.label + ".\n";
    }
}
