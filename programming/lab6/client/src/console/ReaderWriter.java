package console;

public interface ReaderWriter {
    Integer readInt();
    Long readLong();
    Float readFloat();
    String readLine();
    void write(String text);
    void printError(String text);
    String getValidatedValue(String message);
}
