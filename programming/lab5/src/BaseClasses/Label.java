package BaseClasses;

/**
 * The {@code Label} class represents the label of the music band.
 *
 * @author Egorova Varvara
 */
public class Label {
    /**
     * Number of bands in the label.
     */
    private long bands;

    /**
     * Constructor that creates label.
     * @param bands the number of bands in the label
     */
    public Label(long bands){
        this.bands = bands;
    }

    /**
     * Use to get bands of the label.
     * @return bands of the label
     */
    public long getBands(){
        return this.bands;
    }

    /**
     * Use to set bands of the label.
     * @param bands given bands
     */
    public void setBands(long bands){
        this.bands = bands;
    }

    @Override
    public String toString(){
        return String.valueOf(this.bands);
    }

    /**
     * Use to check if to labels are the same.
     * @param label the label to be compared
     * @return boolean
     */
    public boolean equals(Label label){
        return (label.getBands() == this.bands);
    }
}
