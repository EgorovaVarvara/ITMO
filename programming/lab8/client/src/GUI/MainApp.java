package GUI;

import baseClasses.MusicBand;
import commands.*;
import connectionUtils.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import utils.ScriptExecutor;

import java.io.File;
import java.text.MessageFormat;
import java.util.*;


public class MainApp {

    private Localizer localizer;
    private Sender sender;
    private Runnable authCallback;
    private AddController addController;
    private VisualWindow visualWindow;
    private int waitTime = 5000;
    private Thread refresher;
    private final HashMap<String, Locale> localeMap = new HashMap<>() {{
        put("Русский", new Locale("ru"));
        put("čeština", new Locale("cs"));
        put("Український", new Locale("uk"));
        put("Español", new Locale("es", "DOM"));
    }};
    private Stage stage;
    private volatile boolean isRefreshing = false;

    @FXML
    public Button infoButton;
    @FXML
    public Button executeScriptButton;
    @FXML
    public Button clearButton;
    @FXML
    public Button sumOfNumberOfParticipantsButton;
    @FXML
    public Label userName;
    @FXML
    public Button filterLessThanNumberOfParticipants;
    @FXML
    public TableView<MusicBand> tableTable;
    @FXML
    public TableColumn<MusicBand, Integer> idColumn;
    @FXML
    public TableColumn<MusicBand, String> nameColumn;
    @FXML
    public TableColumn<MusicBand, Long> xColumn;
    @FXML
    public TableColumn<MusicBand, Float> yColumn;
    @FXML
    public TableColumn<MusicBand, String> dateColumn;
    @FXML
    public TableColumn<MusicBand, Integer> numberOfParticipantsColumn;
    @FXML
    public TableColumn<MusicBand, String> musicGenreColumn;
    @FXML
    public TableColumn<MusicBand, Long> labelColumn;
    @FXML
    public TableColumn<MusicBand, String> ownerColumn;
    @FXML
    public Button AddButton;
    @FXML
    public Button AddIfMaxButton;
    @FXML
    public Button AddIfMinButton;
    @FXML
    public Button RemoveLowerButton;
    @FXML
    public Button RemoveByIdButton;
    @FXML
    public Button VisualisationButton;
    @FXML
    public Button ExitButton;
    @FXML
    public ComboBox<String> languageComboBox;
    @FXML
    public TextField numberOfParticipantsField;
    @FXML
    public Button updateIdButton;


    @FXML
    public void initialize() {
        languageComboBox.setItems(FXCollections.observableArrayList(localeMap.keySet()));
        languageComboBox.setValue(Client.getCurrentLanguage());
        languageComboBox.setStyle("-fx-font: 14px \"DejaVu Sans Bold\";");
        languageComboBox.setOnAction(event -> {
            localizer.setBundle(ResourceBundle.getBundle("GUI/locales/gui", localeMap.get(languageComboBox.getValue())));
            Client.setCurrentLanguage(languageComboBox.getValue());
            changeLanguage();
        });
        idColumn.setCellValueFactory(musicBand -> new SimpleIntegerProperty(musicBand.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(musicBand -> new SimpleStringProperty(musicBand.getValue().getName()));
        xColumn.setCellValueFactory(musicBand -> new SimpleLongProperty(musicBand.getValue().getCoordinates().getX()).asObject());
        yColumn.setCellValueFactory(musicBand -> new SimpleFloatProperty(musicBand.getValue().getCoordinates().getY()).asObject());
        dateColumn.setCellValueFactory(musicBand -> new SimpleStringProperty(localizer.getDate(musicBand.getValue().getCreationDate())));
        numberOfParticipantsColumn.setCellValueFactory(musicBand -> new SimpleIntegerProperty(musicBand.getValue().getNumberOfParticipants()).asObject());
        musicGenreColumn.setCellValueFactory(musicBand -> new SimpleStringProperty(musicBand.getValue().getMusicGenre().toString()));
        labelColumn.setCellValueFactory(musicBand -> new SimpleLongProperty(musicBand.getValue().getLabel().getBands()).asObject());
        ownerColumn.setCellValueFactory(musicBand -> new SimpleStringProperty(musicBand.getValue().getUser_login()));

        tableTable.getSortOrder().add(idColumn);
        idColumn.setComparator(Integer::compareTo);
        tableTable.setRowFactory(tableView -> {
            var row = new TableRow<MusicBand>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !row.isEmpty()) {
                    doubleClickUpdate(row.getItem());
                }
            });
            return row;
        });
    }


    @FXML
    public void info() {
        try {
            Command info = new InfoCommand();
            info.setUser(Client.getUser());
            var response = sender.sendAndReceive(info);
            var message = MessageFormat.format(localizer.getKeyString("InfoResult"), response.getType(), response.getAmountOfElements(), localizer.getDate(response.getInitializationTime()));
            DialogManager.createAlert(localizer.getKeyString("Info"), message);
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localizer);
        }
    }

    @FXML
    public void executeScript() {
        String filepath;
        Optional<String> path = DialogManager.getFilePath(localizer);
        if (path.isPresent()) {
            filepath = path.orElse("");
            try {
                File file = new File(filepath);
                if (file.exists()) {
                    ScriptExecutor se = new ScriptExecutor(file).readScript();
                    ArrayList<Command> commands = se.getCommandList();
                    if (!commands.isEmpty()) {
                        var errors = new ArrayList<String>();
                        commands.forEach(command -> {
                            command.setUser(Client.getUser());
                            var response = sender.sendAndReceive(command);
                            if (!response.getStatus().equals(ResponseStatus.OK)) {
                                errors.add("-" + localizer.getKeyString("CommandExecError") + ". " + localizer.getKeyString("CheckScriptErr"));
                            }
                        });
                        if (!errors.isEmpty()) {
                            DialogManager.alert(String.join("\n", errors), localizer);
                        } else {
                            DialogManager.info("ScriptExecutionSuc", localizer);
                        }
                    } else {
                        DialogManager.alert("EmptyFileErr", localizer);
                    }
                } else {
                    DialogManager.alert("FileNotFoundException", localizer);
                }
            } catch (Exception e) {
                DialogManager.alert("ScriptExecutionErr", localizer);
            }
        }
    }

    @FXML
    public void clear() {
        try {
            Command clear = new ClearCommand();
            clear.setUser(Client.getUser());
            var response = sender.sendAndReceive(clear);
            if (response.getStatus().equals(ResponseStatus.OK)) {
                DialogManager.createAlert(localizer.getKeyString("Clear"), localizer.getKeyString("ClearSuc"));
            } else {
                DialogManager.createAlert(localizer.getKeyString("Clear"), localizer.getKeyString("ClearFail"));
            }
            loadCollection();
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localizer);
        }
    }

    @FXML
    public void sumOfNumberOfParticipants() {
        try {
            Command sum = new SumOfNumberOfParticipantsCommand();
            sum.setUser(Client.getUser());
            var response = sender.sendAndReceive(sum);
            DialogManager.createAlert(localizer.getKeyString("SumOfNumberOfParticipants"), localizer.getKeyString("SumSuc") + response.getResponse());
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localizer);
        }
    }

    @FXML
    public void add() {
        addController.clear();
        addController.show();
        var musicBand = addController.getMusicBand();
        if (musicBand != null) {
            try {
                var addCommand = new AddCommand(musicBand);
                var response = sender.sendAndReceive(addCommand);
                if (response.getStatus().equals(ResponseStatus.OK)) {
                    DialogManager.createAlert(localizer.getKeyString("Add"), localizer.getKeyString("AddResult"));
                    loadCollection();
                }
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localizer);
            }
        }
    }

    @FXML
    public void addIfMax() {
        addController.clear();
        addController.show();
        var musicBand = addController.getMusicBand();
        if (musicBand != null) {
            try {
                var addIfMax = new AddIfMaxCommand(musicBand);
                var response = sender.sendAndReceive(addIfMax);
                if (response.getStatus().equals(ResponseStatus.OK)) {
                    DialogManager.createAlert(localizer.getKeyString("Add"), localizer.getKeyString("AddResult"));
                    loadCollection();
                } else {
                    DialogManager.createAlert(localizer.getKeyString("Add"), localizer.getKeyString("AddNotMax"));
                }
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localizer);
            }
        }
    }

    @FXML
    public void addIfMin() {
        addController.clear();
        addController.show();
        var musicBand = addController.getMusicBand();
        if (musicBand != null) {
            try {
                var addIfMin = new AddIfMinCommand(musicBand);
                var response = sender.sendAndReceive(addIfMin);
                if (response.getStatus().equals(ResponseStatus.OK)) {
                    DialogManager.createAlert(localizer.getKeyString("Add"), localizer.getKeyString("AddResult"));
                    loadCollection();
                } else {
                    DialogManager.createAlert(localizer.getKeyString("Add"), localizer.getKeyString("AddNotMin"));
                }
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localizer);
            }
        }
    }

    @FXML
    public void removeLower() {
        addController.clear();
        addController.show();
        var musicBand = addController.getMusicBand();
        if (musicBand != null) {
            try {
                var remove = new RemoveLowerCommand(musicBand);
                var response = sender.sendAndReceive(remove);
                if (response.getStatus().equals(ResponseStatus.OK)) {
                    DialogManager.createAlert(localizer.getKeyString("RemoveLower"), localizer.getKeyString("RemoveLowerSuc"));
                    loadCollection();
                } else {
                    DialogManager.createAlert(localizer.getKeyString("RemoveLower"), localizer.getKeyString("RemoveLowerFail"));
                }
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localizer);
            }
        }
    }

    @FXML
    public void filterLessThanNumberOfParticipants() {
        int numberOfParticipants;
        try {
            var input = numberOfParticipantsField.getText();
            if (!(input.isEmpty() || input.isBlank())) {
                numberOfParticipants = Integer.parseInt(input);
                if (numberOfParticipants <= 0) {
                    DialogManager.createAlert(localizer.getKeyString("FilterLessThanNumberOfParticipants"), localizer.getKeyString("NumberOfParticipants") + " " + localizer.getKeyString("MustBeGreaterThanZero"));
                } else {
                    var filterCommand = new FilterLessThanNumberOfParticipantsCommand(numberOfParticipants);
                    filterCommand.setUser(Client.getUser());
                    var response = sender.sendAndReceive(filterCommand);
                    if (response.getStatus().equals(ResponseStatus.OK)) {
                        setCollection(response.getCollection());
                    }
                }
            } else {
                DialogManager.alert("EnterNumberOfParticipants", localizer);
            }
        } catch (NumberFormatException e) {
            DialogManager.createAlert(localizer.getKeyString("FilterLessThanNumberOfParticipants"), localizer.getKeyString("NumberOfParticipants") + " " + localizer.getKeyString("MustBeNumeric"));
        }
    }

    @FXML
    public void updateId() {
        int ID;
        MusicBand musicBand;
        Optional<Integer> id = DialogManager.getId(localizer);
        if (id.isPresent()) {
            try {
                ID = id.orElse(0);
                musicBand = Client.getCollection().stream()
                        .filter(m -> m.getId() == ID)
                        .findAny()
                        .orElse(null);
                if (musicBand == null) throw new NoSuchElementException();
                addController.fill(musicBand);
                addController.show();

                var updated = addController.getMusicBand();
                if (updated != null) {
                    updated.setId(musicBand.getId());
                    var update = new UpdateIdCommand(updated);
                    update.setUser(Client.getUser());
                    var response = sender.sendAndReceive(update);
                    if (response.getStatus().equals(ResponseStatus.OK)) {
                        DialogManager.createAlert(localizer.getKeyString("UpdateId"), localizer.getKeyString("UpdateSuc"));
                    } else if (response.getStatus().equals(ResponseStatus.ERROR)) {
                        DialogManager.createAlert(localizer.getKeyString("UpdateId"), localizer.getKeyString("UpdateErr"));
                    } else {
                        DialogManager.alert("BadOwnerError", localizer);
                    }
                    loadCollection();
                }
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localizer);
            } catch (IllegalArgumentException e) {
                DialogManager.alert("BadOwnerError", localizer);
            } catch (NoSuchElementException e) {
                DialogManager.createAlert(localizer.getKeyString("Error"), localizer.getKeyString("NoSuchElement"));
            }
        }
    }

    @FXML
    public void removeById() {
        int ID;
        Optional<Integer> id = DialogManager.getId(localizer);
        if (id.isPresent()) {
            ID = id.orElse(0);
            try {
                var remove = new RemoveByIdCommand(ID);
                remove.setUser(Client.getUser());
                var response = sender.sendAndReceive(remove);
                if (response.getStatus().equals(ResponseStatus.OK)) {
                    DialogManager.info("RemoveByIDSuc", localizer);
                } else if (response.getStatus().equals(ResponseStatus.ERROR)) {
                    DialogManager.alert("UpdateErr", localizer);
                } else {
                    DialogManager.alert("BadOwnerError", localizer);
                }
                loadCollection();
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localizer);
            }
        }
    }

    @FXML
    public void visualise() {
        visualWindow.show(true);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    public void doubleClickUpdate(MusicBand item) {
        doubleClickUpdate(item, true);
    }

    private void doubleClickUpdate(MusicBand musicBand, boolean ignore) {
        if (ignore && !Objects.equals(musicBand.getUser_login(), Client.getUser().getLogin())) return;

        addController.fill(musicBand);
        addController.show();

        var updatedProduct = addController.getMusicBand();
        if (updatedProduct != null) {
            updatedProduct.setId(musicBand.getId());
            updatedProduct.setUser_login(Client.getUser().getLogin());

            try {
                var update = new UpdateIdCommand(updatedProduct);
                update.setUser(Client.getUser());
                var response = sender.sendAndReceive(update);
                if (response.getStatus().equals(ResponseStatus.OK)) {
                    DialogManager.createAlert(localizer.getKeyString("UpdateId"), localizer.getKeyString("UpdateSuc"));
                } else if (response.getStatus().equals(ResponseStatus.ERROR)) {
                    DialogManager.createAlert(localizer.getKeyString("UpdateId"), localizer.getKeyString("UpdateErr"));
                } else {
                    DialogManager.alert("BadOwnerError", localizer);
                }
                loadCollection();
            } catch (NullPointerException e) {
                DialogManager.alert("RefreshLost", localizer);
            }
        }
    }

    public void setAuthCallback(Runnable authCallback) {
        this.authCallback = authCallback;
    }

    public void setContext(Sender sender, Localizer localizer, Stage stage) {
        this.sender = sender;
        this.localizer = localizer;
        this.stage = stage;

        languageComboBox.setValue(Client.getCurrentLanguage());
        localizer.setBundle(ResourceBundle.getBundle("GUI/locales/gui", localeMap.get(Client.getCurrentLanguage())));
        changeLanguage();

        userName.setText(Client.getUser().getLogin());
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    private void changeLanguage() {
        infoButton.setText(localizer.getKeyString("Info"));
        executeScriptButton.setText(localizer.getKeyString("ExecuteScript"));
        clearButton.setText(localizer.getKeyString("Clear"));
        sumOfNumberOfParticipantsButton.setText(localizer.getKeyString("SumOfNumberOfParticipants"));
        filterLessThanNumberOfParticipants.setText(localizer.getKeyString("FilterLessThanNumberOfParticipants"));
        nameColumn.setText(localizer.getKeyString("Name"));
        xColumn.setText(localizer.getKeyString("CoordinateX"));
        yColumn.setText(localizer.getKeyString("CoordinateY"));
        dateColumn.setText(localizer.getKeyString("CreationDate"));
        numberOfParticipantsColumn.setText(localizer.getKeyString("NumberOfParticipants"));
        musicGenreColumn.setText(localizer.getKeyString("MusicGenre"));
        labelColumn.setText(localizer.getKeyString("Bands"));
        ownerColumn.setText(localizer.getKeyString("Owner"));
        AddButton.setText(localizer.getKeyString("Add"));
        AddIfMaxButton.setText(localizer.getKeyString("AddIfMax"));
        AddIfMinButton.setText(localizer.getKeyString("AddIfMin"));
        RemoveLowerButton.setText(localizer.getKeyString("RemoveLower"));
        RemoveByIdButton.setText(localizer.getKeyString("RemoveById"));
        updateIdButton.setText(localizer.getKeyString("UpdateId"));
        VisualisationButton.setText(localizer.getKeyString("Visualisation"));
        ExitButton.setText(localizer.getKeyString("Exit"));
        addController.changeLanguage();
        loadCollection();
    }

    public void setLocalizer(Localizer localizer) {
        this.localizer = localizer;
    }

    private void setCollection(HashSet<MusicBand> collection) {
        if (!SetComparator.compare(collection, Client.getCollection())) {
            tableTable.setItems(FXCollections.observableArrayList(collection));
            tableTable.getSortOrder().add(idColumn);
            idColumn.setComparator(Integer::compareTo);
            Client.setCollection(collection);
        }
    }

    private void loadCollection() {
        try {
            Command showCommand = new ShowCommand();
            showCommand.setUser(Client.getUser());
            var response = sender.sendAndReceive(showCommand);
            if (response.getStatus().equals(ResponseStatus.OK)) {
                setCollection(response.getCollection());
            }
        } catch (NullPointerException e) {
            DialogManager.alert("RefreshLost", localizer);
        }
    }

    public void refresh() {
        refresher = new Thread(() -> {
            while (isRefreshing()) {
                Platform.runLater(this::loadCollection);
                try {
                    Thread.sleep(getWaitTime());
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted, Failed to complete operation");
                    break;
                }
            }
        });
        refresher.start();
    }

    private int getWaitTime() {
        return this.waitTime;
    }

    private void stopRefreshing() {
        refresher.interrupt();
    }

    public void setAddController(AddController addController) {
        this.addController = addController;
        addController.changeLanguage();
    }

    public void setVisualWindow(VisualWindow visualWindow) {
        this.visualWindow = visualWindow;
    }
}
