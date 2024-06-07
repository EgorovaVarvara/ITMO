package forms;

import connectionUtils.User;
import console.*;
import utils.ExecuteFileManager;
import utils.ReadManager;

public class UserForm extends Form{
    private final ReaderWriter console;
    private final UserInput scanner;
    public UserForm(ReaderWriter console){
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    @Override
    public User build() {
        ReadManager readManager = new ReadManager(console);
        console.write("Вы уже зарегестрированны?");
        while (true){
            console.write("Введите Y или N: ");
            String answer = console.readLine();
            if (!(answer.equals("Y") || answer.equals("N"))) {
                System.out.println("Неверный ответ. Введите Y, если зарегестрированны, или N, если нет");
            } else {
                return new User(
                        readManager.readLogin(),
                        readManager.readPassword(),
                        answer.equals("Y")
                );
            }
        }
    }
}
