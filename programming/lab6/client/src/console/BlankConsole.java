package console;

public class BlankConsole implements ReaderWriter{

    @Override
    public Integer readInt() {
        return null;
    }

    @Override
    public Long readLong() {
        return null;
    }

    @Override
    public Float readFloat() {
        return null;
    }

    @Override
    public String readLine() {
        return null;
    }

    @Override
    public void write(String text) {

    }

    @Override
    public void printError(String text) {

    }

    @Override
    public String getValidatedValue(String message) {
        return null;
    }
}
