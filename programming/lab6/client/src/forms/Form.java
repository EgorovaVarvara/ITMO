package forms;


/**
 * Abstract class {@code Form} that contains one method for building some field of MusicBand class
 * @author Egorova Varvara
 * @see baseClasses.MusicBand
 * @param <T>
 */
public abstract class Form<T> {
    /**
     * Abstract method for building field
     * @return parameter of type <T>
     */
    public abstract T build();
}
