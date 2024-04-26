package forms;

import baseClasses.Coordinates;
import baseClasses.Label;
import baseClasses.MusicBand;
import baseClasses.MusicGenre;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code MusicBandForm} needs to create music band
 * @see forms.Form
 * @see MusicBand
 * @author Egorova Varvara
 */

public class MusicBandForm extends Form<MusicBand> {
    private final ReaderWriter console;
    private final UserInput scanner;
    public MusicBandForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public MusicBand build() {
        ReadManager readManager = new ReadManager(console);
        return new MusicBand(
                readManager.readName(),
                readCoordinates(),
                readManager.readNumberOfParticipants(),
                readMusicGenre(),
                readLabel()
        );
    }
    private Coordinates readCoordinates() {
        return new CoordinatesForm(console).build();
    }
    private MusicGenre readMusicGenre() {
        return new MusicGenreForm(console).build();
    }
    private Label readLabel() {
        return new LabelForm(console).build();
    }
}
