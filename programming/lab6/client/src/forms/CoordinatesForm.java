package forms;

import baseClasses.Coordinates;
import console.*;
import errors.InvalidFormException;
import utils.ExecuteFileManager;
import utils.ReadManager;

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
    public Coordinates build() throws InvalidFormException {
        ReadManager readManager = new ReadManager(console);
        return new Coordinates(
                readManager.readCoordinateX(),
                readManager.readCoordinateY()
        );
    }
}
