package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class DialogManager {
    private static AlertWindow alertWindow;
    private static DialogWindow dialogWindow;
    static {
        FXMLLoader alertLoader = new FXMLLoader(DialogManager.class.getResource("AlertWindow.fxml"));
        Parent alertRoot = loadFxml(alertLoader);
        Scene alertScene = new Scene(alertRoot);
        Stage alertStage = new Stage();
        alertStage.setScene(alertScene);
        alertStage.setResizable(false);
        alertWindow = alertLoader.getController();
        alertWindow.setStage(alertStage);

        FXMLLoader dialogLoader = new FXMLLoader(DialogManager.class.getResource("DialogWindow.fxml"));
        Parent dialogRoot = loadFxml(dialogLoader);
        Scene dialogScene = new Scene(dialogRoot);
        Stage dialogStage = new Stage();
        dialogStage.setScene(dialogScene);
        dialogStage.setResizable(false);
        dialogWindow = dialogLoader.getController();
        dialogWindow.setStage(dialogStage);
    }

    public static void alert(String title, Localizer localizer) {
        try {
            alertWindow.alert(localizer.getKeyString("Error"), localizer.getKeyString(title));
        } catch (FileNotFoundException e){
            throw new RuntimeException();
        }
    }

    public static void info(String title, Localizer localizer) {
        try {
            alertWindow.info(localizer.getKeyString("Info"), localizer.getKeyString(title));
        }catch (FileNotFoundException e){
            throw new RuntimeException();
        }
    }

    public static void createAlert(String title, String content) {
        try {
            alertWindow.info(title, content);
        } catch (FileNotFoundException e){
            throw new RuntimeException();
        }
    }

    public static Optional<Integer> getId(Localizer localizer) {
        dialogWindow.clear();
        dialogWindow.setLocalizer(localizer);
        dialogWindow.show();
        dialogWindow.setOptionFIlePath(false);
        return Optional.of(dialogWindow.getId());
    }
    public static Optional<String> getFilePath(Localizer localizer){
        dialogWindow.clear();
        dialogWindow.setLocalizer(localizer);
        dialogWindow.setOptionFIlePath(true);
        dialogWindow.show();
        return Optional.of(dialogWindow.getFilePath());
    }
    private static Parent loadFxml(FXMLLoader loader) {
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            System.exit(1);
        }
        return parent;
    }
}
