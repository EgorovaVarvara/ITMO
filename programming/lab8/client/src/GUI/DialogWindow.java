package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DialogWindow {
    private Integer id;
    private String filePath;
    private Stage stage;
    private boolean isFilePath;
    private Localizer localizer;
    @FXML
    public Label titleLabel;
    @FXML
    public TextField textField;
    @FXML
    public Label errorLabel;
    @FXML
    public Button okButton;
    @FXML
    public ImageView backButton;

    @FXML
    public void initialize() {
        backButton.setOnMouseClicked(event -> stage.close());
    }

    public int getId() {
        isFilePath = false;
//        try{
//            textField.setText(textField.getText());
//            id = Integer.parseInt(textField.getText());
//            if (id <= 0){
//                DialogManager.alert("MustBeGreaterThanZero", localizer);
//                id = null;
//            }
//        } catch (NumberFormatException e){
//            DialogManager.alert("MustBeNumeric", localizer);
//        }
//        return id;
        int currentId = id;
        id = null;
        return currentId;
    }

    public String getFilePath() {
        isFilePath = true;
//        stage.showAndWait();
//        if (textField.getText().isEmpty() || textField.getText().isBlank()){
//            DialogManager.alert("CantBeEmpty", localizer);
//        }
//        return filePath;
        var currentFilePath = filePath;
        filePath = null;
        return currentFilePath;
    }

    public void clear() {
        titleLabel.setText("");
        errorLabel.setText("");
        textField.clear();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void send() {
        if (isFilePath) {
            textField.setText(textField.getText());
            filePath = textField.getText();
            if (textField.getText().isBlank() || textField.getText().isEmpty()) {
                DialogManager.alert("CantBeEmpty", localizer);
            } else {
                stage.close();
            }
        } else {
            textField.setText(textField.getText());
            try {
                id = Integer.parseInt(textField.getText());
                if (id <= 0) {
                    DialogManager.alert("MustBeGreaterThanZero", localizer);
                    id = null;
                } else {
                    stage.close();
                }
            } catch (NumberFormatException e) {
                DialogManager.alert("MustBeNumeric", localizer);
            }
        }
    }
    public void setOptionFIlePath(boolean flag){
        this.isFilePath = flag;
    }
    public void setLocalizer(Localizer localizer){
        this.localizer = localizer;
    }
    public void show() {
        if (!stage.isShowing()) {
            titleLabel.setText(isFilePath ? localizer.getKeyString("EnterFilePath") : localizer.getKeyString("EnterId"));
            stage.showAndWait();
        }
    }
}
