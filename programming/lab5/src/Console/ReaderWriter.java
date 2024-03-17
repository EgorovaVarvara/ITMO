package Console;

import java.io.File;

public interface ReaderWriter {
    int readInt();
    long readLong();
    float readFloat();
    String readLine();
    void writeLine(String text);
    void write(String text);
    String getValidatedValue(String message);
    File readFileName();
}
