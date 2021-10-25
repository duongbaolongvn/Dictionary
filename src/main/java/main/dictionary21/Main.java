package main.dictionary21;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dic.fxml"));
        stage.setTitle("E-V Dictionary");
        stage.setScene(new Scene(root, 1000, 1000));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}