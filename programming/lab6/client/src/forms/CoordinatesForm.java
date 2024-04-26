package forms;

import baseClasses.Coordinates;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code CoordinatesForm} needs to create coordinates field
 * @see forms.Form
 * @see Coordinates
 * @author Egorova Varvara
 */
public class CoordinatesForm extends Form<Coordinates>{
    private final ReaderWriter console;
    private final UserInput scanner;
    public CoordinatesForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public Coordinates build() {
        ReadManager readManager = new ReadManager(console);
        return new Coordinates(
                readManager.readCoordinateX(),
                readManager.readCoordinateY()
        );
    }
}
