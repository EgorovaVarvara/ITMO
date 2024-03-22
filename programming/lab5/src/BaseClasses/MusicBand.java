package BaseClasses;

import Collection.IdGenerator;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class MusicBand implements Comparable<MusicBand>, Serializable {
    private int id; //>0; уникальное; генерируется автоматически
    private String name; //not null; не пустая строка
    private Coordinates coordinates; //not null
    private java.time.LocalDateTime creationDate = java.time.LocalDateTime.now(); //not null, генерируется автоматически
    private int numberOfParticipants; // >0
    private MusicGenre musicGenre; //not null
    private Label label; //not null
    IdGenerator idGenerator = new IdGenerator();
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
    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Coordinates getCoordinates(){
        return this.coordinates;
    }
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    public java.time.LocalDateTime getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(){
        this.creationDate = java.time.LocalDateTime.now();
    }
    public int getNumberOfParticipants(){
        return this.numberOfParticipants;
    }
    public void setNumberOfParticipants(int numberOfParticipants){
        this.numberOfParticipants = numberOfParticipants;
    }
    public MusicGenre getMusicGenre(){
        return this.musicGenre;
    }
    public void setMusicGenre(MusicGenre musicGenre){
        this.musicGenre = musicGenre;
    }
    public Label getLabel(){
        return this.label;
    }
    public void setLabel(Label label){
        this.label = label;
    }
    public Object[] getFields(){
        return new Object[]{id, name, coordinates, creationDate, numberOfParticipants, musicGenre, label};
    }

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
