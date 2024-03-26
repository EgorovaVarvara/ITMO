package BaseClasses;

/**
 * The {@code Coordinates} class represents the coordinates of music band.
 *
 * @author Egorova Varvara
 */
public class Coordinates {
    /**
     * The `x` coordinate.
     * Cant be null.
     */
    private Long x; //not null
    /**
     * The `y` coordinate.
     */
    private float y;

    /**
     * Constructor to create coordinates with given values.
     * @param x the `x` coordinate
     * @param y the `y` coordinate
     * @throws IllegalArgumentException if `x` is null
     */
    public Coordinates(Long x, float y) throws IllegalArgumentException{
        if (x == null) throw new IllegalArgumentException("Invalid coordinates");
        this.x = x;
        this.y = y;
    }

    /**
     * Use to get `x` coordinate.
     * @return `x` coordinate
     */
    public Long getX(){
        return this.x;
    }

    /**
     * Use to get `y` coordinate.
     * @return `y` coordinate
     */
    public float getY(){
        return this.y;
    }

    /**
     * Use to set `x` coordinate.
     * @param x given `x` coordinate
     */
    public void setX(Long x){
        this.x = x;
    }

    /**
     * Use to set `y` coordinate.
     * @param y given `y` coordinate
     */
    public void setY(float y){
        this.y = y;
    }

    /**
     * Use to check if two Coordinates are the same.
     * @param c coordinates to be compared
     * @return boolean
     */
    public boolean equals(Coordinates c){
        return c.getX() == this.x && c.getY() == this.y;
    }
    @Override
    public String toString(){
        return "(" + x + "; " + y + ")";
    }
}
