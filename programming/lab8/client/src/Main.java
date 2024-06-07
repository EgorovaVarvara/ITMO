import GUI.*;
import connectionUtils.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {
    private static final InetAddress serverAddress;
    private static Localizer localizer;
    private static Sender sender;
    private static Stage mainStage;
    static {
        try {
            serverAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int serverPort = 2712;

    public static void main(String[] args) {
        try {
            sender = new Sender(serverAddress, serverPort);
            launch(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        try {
//            sender = new Sender(serverAddress, serverPort);
//            Client.setSender(sender);
//            Client.enter();
//            System.out.println("Для получения справки по доступным командам введите help");
//            Client.run();
//        } catch (NoSuchElementException e) {
//            System.out.println("Ну хорошо же все начиналось...");
//        } catch (SocketException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        localizer = new Localizer(ResourceBundle.getBundle("GUI/locales/gui", new Locale("ru", "RU")));
        mainStage = stage;
        authStage();
    }
    private void authStage(){
        var authLoader = new FXMLLoader(getClass().getResource("GUI/enter.fxml"));
        Parent authRoot = loadFxml(authLoader);
        Enter enter = authLoader.getController();
        enter.setCallback(this::startMain);
        enter.setSender(sender);
        enter.setLocalizer(localizer);
        mainStage.setScene(new Scene(authRoot));
        mainStage.setTitle(localizer.getKeyString("MusicBands"));
        mainStage.setResizable(false);
        mainStage.show();
    }
    private void startMain(){
        var mainLoader = new FXMLLoader(getClass().getResource("GUI/Main.fxml"));
        var addLoader = new FXMLLoader(getClass().getResource("GUI/addController.fxml"));
        var visLoader = new FXMLLoader(getClass().getResource("GUI/VisualWindow.fxml"));

        var mainRoot = loadFxml(mainLoader);
        var addRoot = loadFxml(addLoader);
        var visRoot = loadFxml(visLoader);

        var addScene = new Scene(addRoot);
        var addStage = new Stage();
        addStage.setScene(addScene);
        addStage.setResizable(false);
        addStage.setTitle(localizer.getKeyString("MusicBands"));
        AddController addController = addLoader.getController();
        addController.setStage(addStage);
        addController.setLocalizer(localizer);

        var visScene = new Scene(visRoot);
        var visStage = new Stage();
        visStage.setScene(visScene);
        visStage.setResizable(false);
        visStage.setTitle(localizer.getKeyString("MusicBands"));
        VisualWindow visualWindow = visLoader.getController();
        visualWindow.setLocalizer(localizer);
        visualWindow.setSender(sender);
        visualWindow.setStage(visStage);

        MainApp mainApp = mainLoader.getController();
        mainApp.setAddController(addController);
        mainApp.setVisualWindow(visualWindow);
        mainApp.setContext(sender, localizer, mainStage);
        mainApp.setAuthCallback(this::authStage);
        mainStage.setScene(new Scene(mainRoot));
        mainApp.setRefreshing(true);
        mainApp.refresh();
        mainStage.show();
    }
    private Parent loadFxml(FXMLLoader loader) {
    Parent parent = null;
    try {
      parent = loader.load();
    } catch (IOException e) {
      System.exit(1);
    }
    return parent;
  }
}

