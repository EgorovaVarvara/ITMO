package forms;

import baseClasses.Coordinates;
import baseClasses.Label;
import baseClasses.MusicBand;
import baseClasses.MusicGenre;
import console.*;
import errors.InvalidFormException;
import utils.ExecuteFileManager;
import utils.ReadManager;

import java.time.LocalDateTime;

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
    public MusicBand build() throws InvalidFormException {
        ReadManager readManager = new ReadManager(console);
        LocalDateTime localDateTime = LocalDateTime.now();
        return new MusicBand(
                readManager.readName(),
                readCoordinates(),
                readManager.readNumberOfParticipants(),
                readMusicGenre(),
                readLabel()
        );
    }
    private Coordinates readCoordinates() throws InvalidFormException {
        return new CoordinatesForm(console).build();
    }
    private MusicGenre readMusicGenre() throws InvalidFormException {
        return new MusicGenreForm(console).build();
    }
    private Label readLabel() throws InvalidFormException{
        return new LabelForm(console).build();
    }
}
