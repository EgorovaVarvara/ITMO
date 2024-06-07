package GUI;

import baseClasses.Coordinates;
import baseClasses.MusicBand;
import baseClasses.MusicGenre;
import connectionUtils.Client;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AddController {

    private Stage stage;
    private MusicBand musicBand;
    private Localizer localizer;

    @FXML
    public Button add;
    @FXML
    public ImageView backButton;
    @FXML
    public Label nameLabel;
    @FXML
    public TextField nameField;
    @FXML
    public Label XLabel;
    @FXML
    public TextField XField;
    @FXML
    public Label YLabel;
    @FXML
    public TextField YField;
    @FXML
    public Label numberOfParticipantsLabel;
    @FXML
    public TextField numberOfParticipantsField;
    @FXML
    public Label musicGenreLabel;
    @FXML
    public ChoiceBox<String> musicGenreChoiceBox;
    @FXML
    public Label bandsLabel;
    @FXML
    public TextField bandsField;

    @FXML
    public void initialize() {
        backButton.setOnMouseClicked(event -> stage.close());
        var musicGenres = FXCollections.observableArrayList(Arrays.stream(MusicGenre.values()).map(MusicGenre::toString).collect(Collectors.toList()));
        musicGenreChoiceBox.setItems(musicGenres);
        musicGenreChoiceBox.setStyle("-fx-background-color: #C29292; -fx-font: 14px \"DejaVu Sans Bold\";");

    }

    @FXML
    public void add() {
        nameField.setText(nameField.getText());
        XField.setText(XField.getText());
        YField.setText(YField.getText());
        numberOfParticipantsField.setText(numberOfParticipantsField.getText());
        bandsField.setText(bandsField.getText());

        var errors = new ArrayList<String>();

        String name = nameField.getText();
        if (nameField.getText().isEmpty() || nameField.getText().isBlank()) {
            name = null;
            errors.add("-" + localizer.getKeyString("Name") + " " + localizer.getKeyString("CannotBeEmpty"));
        }

        Long X = null;
        try {
            X = Long.parseLong(XField.getText());
        } catch (NumberFormatException e) {
            errors.add("-" + localizer.getKeyString("CoordinateX") + " " + localizer.getKeyString("MustBeInteger"));
        }

        Float Y = null;
        try {
            Y = Float.parseFloat(YField.getText());
        } catch (NumberFormatException e) {
            errors.add("-" + localizer.getKeyString("CoordinateY") + " " + localizer.getKeyString("MustBeNumeric"));
        }

        Integer numberOfParticipants = null;
        try {
            numberOfParticipants = Integer.parseInt(numberOfParticipantsField.getText());
            if (numberOfParticipants <= 0) {
                errors.add("-" + localizer.getKeyString("NumberOfParticipants") + " " + localizer.getKeyString("MustBeGreaterThanZero"));
            }
        } catch (NumberFormatException e) {
            errors.add("-" + localizer.getKeyString("NumberOfParticipants") + " " + localizer.getKeyString("MustBeNumeric"));
        }

        MusicGenre musicGenre = null;
        if (musicGenreChoiceBox.getValue() != null) musicGenre = MusicGenre.valueOf(musicGenreChoiceBox.getValue());

        Long bands = null;
        try {
            bands = Long.parseLong(bandsField.getText());
            if (bands <= 0) {
                errors.add("-" + localizer.getKeyString("Bands") + " " + localizer.getKeyString("MustBeGreaterThanZero"));
            }
        } catch (NumberFormatException e) {
            errors.add("-" + localizer.getKeyString("Bands") + " " + localizer.getKeyString("MustBeNumeric"));
        }

        if (!errors.isEmpty()) {
            DialogManager.createAlert(localizer.getKeyString("Error"), String.join("\n", errors));
        } else {
            musicBand = new MusicBand(name, new Coordinates(X, Y), numberOfParticipants, musicGenre, new baseClasses.Label(bands), Client.getUser().getLogin());
            stage.close();
        }
    }
    public void fill(MusicBand musicBand){
        nameField.setText(musicBand.getName());
        XField.setText(musicBand.getCoordinates().getX().toString());
        YField.setText(Float.valueOf(musicBand.getCoordinates().getY()).toString());
        numberOfParticipantsField.setText(Integer.valueOf(musicBand.getNumberOfParticipants()).toString());
        musicGenreChoiceBox.setValue(musicBand.getMusicGenre().toString());
        bandsField.setText(Long.valueOf(musicBand.getLabel().getBands()).toString());
        add.setText(localizer.getKeyString("UpdateId"));
    }

    public void clear(){
        nameField.clear();
        XField.clear();
        YField.clear();
        numberOfParticipantsField.clear();
        bandsField.clear();
        musicGenreChoiceBox.setValue(null);
    }

    public MusicBand getMusicBand() {
        var currentMusicBand = musicBand;
        musicBand = null;
        return currentMusicBand;
    }

    public void changeLanguage(){
        add.setText(localizer.getKeyString("Add"));
        nameLabel.setText(localizer.getKeyString("Name"));
        XLabel.setText(localizer.getKeyString("CoordinateX"));
        YLabel.setText(localizer.getKeyString("CoordinateY"));
        numberOfParticipantsLabel.setText(localizer.getKeyString("NumberOfParticipants"));
        musicGenreLabel.setText(localizer.getKeyString("MusicGenre"));
        bandsLabel.setText(localizer.getKeyString("Bands"));
    }


    public void show() {
        if (!stage.isShowing()) {
            stage.showAndWait();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLocalizer(Localizer localizer) {
        this.localizer = localizer;
    }


}
