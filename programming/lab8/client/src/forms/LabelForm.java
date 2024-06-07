package forms;
import baseClasses.Coordinates;
import baseClasses.Label;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

/**
 * Class {@code LabelForm} needs to create label field
 * @see forms.Form
 * @see Label
 * @author Egorova Varvara
 */
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
    public Label build() {
        ReadManager readManager = new ReadManager(console);
        return new Label(
                readManager.readBands()
        );
    }
}
