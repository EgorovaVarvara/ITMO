package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AlertWindow {
    private Stage stage;
    @FXML
    public Label titleLabel;
    @FXML
    public Label messageLabel;
    @FXML
    public Button okButton;
    @FXML
    public ImageView image;
    @FXML
    public void initialize(){
        okButton.setOnAction(event -> stage.close());
    }
    public void alert(String title, String content) throws FileNotFoundException {
        titleLabel.setText(title);
        messageLabel.setText(content);
        image.setImage(new Image(new FileInputStream("C:\\Users\\evoro\\OneDrive\\Документы\\GitHub\\ITMO\\programming\\lab8\\client\\src\\GUI\\resources\\error.png")));
        stage.showAndWait();
    }
    public void info(String title, String content) throws FileNotFoundException {
        titleLabel.setText(title);
        titleLabel.setTextFill(Paint.valueOf("#C29292"));
        messageLabel.setText(content);
        messageLabel.setTextFill(Paint.valueOf("#C29292"));
        image.setImage(new Image(new FileInputStream("C:\\Users\\evoro\\OneDrive\\Документы\\GitHub\\ITMO\\programming\\lab8\\client\\src\\GUI\\resources\\info.png")));
        stage.showAndWait();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
}
