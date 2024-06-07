package GUI;

import commands.RegistrationCommand;
import connectionUtils.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class Enter {
    private Runnable callback;
    private Localizer localizer;
    private Sender sender;
    private final HashMap<String, Locale> localeMap = new HashMap<>() {{
        put("Русский", new Locale("ru"));
        put("čeština", new Locale("cs"));
        put("Український", new Locale("uk", "UA"));
        put("Español", new Locale("es", "DOM"));
    }};
    @FXML
    private Label loginTitle;
    @FXML
    private TextField loginField;
    @FXML
    private Label passwordTitle;
    @FXML
    private TextField passwordField;
    @FXML
    private Button authButton;
    @FXML
    private Button regButton;
    @FXML
    public ComboBox<String> languageComboBox;


    @FXML
    void initialize() {
        languageComboBox.setItems(FXCollections.observableArrayList(localeMap.keySet()));
        languageComboBox.setValue(Client.getCurrentLanguage());
        languageComboBox.setStyle("-fx-font: 14px \"DejaVu Sans Bold\";");
        languageComboBox.setOnAction(event -> {
            var newLang = languageComboBox.getValue();
            localizer.setBundle(ResourceBundle.getBundle("GUI/locales/gui", localeMap.get(newLang)));
            Client.setCurrentLanguage(newLang);
            changeLanguage();
        });
        passwordField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\S*")) {
                passwordField.setText(oldValue);
            }
        });
    }

    @FXML
    public void auth() {
        try {
            if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()){
                throw new IllegalArgumentException();
            }
            var user = new User(loginField.getText(), passwordField.getText(), true);
            var regCommand = new RegistrationCommand(user);
            var request = new Request(regCommand);
            sender.send(Serializer.serialize(request));
            var response = sender.receive();
            if (response.getStatus().equals(ResponseStatus.OK)){
                Client.setUser(request.getCommandName().getUser());
                Client.setCurrentLanguage(languageComboBox.getValue());
                callback.run();
            } else {
                DialogManager.alert("SignInError", localizer);
            }
        } catch (IOException | ClassNotFoundException e) {
            DialogManager.alert("RefreshLost", localizer);
        } catch (IllegalArgumentException e){
            DialogManager.alert("EmptyFieldsError", localizer);
        }
    }

    @FXML
    public void registration() {
        try {
            if (loginField.getText().isEmpty() || loginField.getText().length() > 40 || passwordField.getText().isEmpty()){
                throw new IllegalArgumentException();
            }
            var user = new User(loginField.getText(), passwordField.getText(), false);
            var regCommand = new RegistrationCommand(user);
            var request = new Request(regCommand);
            sender.send(Serializer.serialize(request));
            var response = sender.receive();
            if (response.getStatus().equals(ResponseStatus.OK)){
                Client.setUser(request.getCommandName().getUser());
                Client.setCurrentLanguage(languageComboBox.getValue());
                callback.run();
            } else {
                DialogManager.alert("UserAlreadyExists", localizer);
            }
        } catch (IOException | ClassNotFoundException e) {
            DialogManager.alert("RefreshLost", localizer);
        } catch (IllegalArgumentException e){
            DialogManager.alert("RegEmptyFields", localizer);
        }
    }

    public void changeLanguage() {
        loginTitle.setText(localizer.getKeyString("LoginField"));
        passwordTitle.setText(localizer.getKeyString("PasswordField"));
        authButton.setText(localizer.getKeyString("SignUpButton"));
        regButton.setText(localizer.getKeyString("CreateAccountButton"));
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setLocalizer(Localizer localizator) {
        this.localizer = localizator;
    }
}
