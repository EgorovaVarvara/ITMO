package forms;
import baseClasses.Label;
import console.*;
import errors.InvalidFormException;
import utils.ExecuteFileManager;
import utils.ReadManager;

public class LabelForm extends Form<Label>{
    private final ReaderWriter console;
    private final UserInput scanner;
    public LabelForm(ReaderWriter console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    @Override
    public Label build() throws InvalidFormException {
        ReadManager readManager = new ReadManager(console);
        return new Label(
                readManager.readBands()
        );
    }
}
