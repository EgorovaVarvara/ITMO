package console;

/**
 * Interface {@code ReaderWriter} contains methods for reading different types and writing
 * @author Egorova Varvara
 */
public interface ReaderWriter {
    Integer readInt();
    Long readLong();
    Float readFloat();
    String readLine();
    void write(String text);
    void printError(String text);
    String getValidatedValue(String message);
}
