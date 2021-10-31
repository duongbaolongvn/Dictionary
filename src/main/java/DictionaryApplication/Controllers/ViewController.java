package DictionaryApplication.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    private Button searchButton, addButton, transButton;
    @FXML
    private Tooltip searchTool, addTool, transTooltip;
    @FXML
    private AnchorPane container;



    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
            setNode(Component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add((Node) node);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showComponent("/Views/SearchView.fxml");


        searchTool.setShowDelay(Duration.seconds(0.5));
        transTooltip.setShowDelay(Duration.seconds(0.5));
        addTool.setShowDelay(Duration.seconds(0.5));



        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("/Views/SearchView.fxml");
            }
        });
        transButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("/Views/APITranslateView.fxml");
            }
        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("/Views/AddView.fxml");
            }
        });
    }

}
