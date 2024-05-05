package forms;

import baseClasses.Coordinates;
import baseClasses.MusicGenre;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;
/**
 * Class {@code MusicGenreForm} needs to create music genre field
 * @see forms.Form
 * @see MusicGenre
 * @author Egorova Varvara
 */
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
    public MusicGenre build() {
        ReadManager readManager = new ReadManager(console);
        return readManager.readMusicGenre();
    }
}
