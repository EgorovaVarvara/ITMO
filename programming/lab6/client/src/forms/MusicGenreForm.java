package forms;

import baseClasses.MusicGenre;
import console.*;
import errors.InvalidFormException;
import utils.ExecuteFileManager;
import utils.ReadManager;

public class MusicGenreForm extends Form<MusicGenre> {
    private final ReaderWriter console;
    private final UserInput scanner;
    public MusicGenreForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public MusicGenre build() throws InvalidFormException {
        ReadManager readManager = new ReadManager(console);
        return readManager.readMusicGenre();
    }
}
