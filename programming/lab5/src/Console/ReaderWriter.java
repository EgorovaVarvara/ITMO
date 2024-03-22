package Console;


/**
 * The {@code ReaderWriter} interface contains methods to read and write.
 *
 * @author Egorova Varvara
 */
public interface ReaderWriter {
    int readInt();
    long readLong();
    float readFloat();
    String readLine();
    void write(String text);
    String getValidatedValue(String message);
}
