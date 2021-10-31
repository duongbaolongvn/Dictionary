package DictionaryApplication.Controllers;

import DictionaryApplication.Alerts.Alerts;
import DictionaryApplication.DictionaryCommandLine.Dictionary;
import DictionaryApplication.DictionaryCommandLine.DictionaryManagement;
import DictionaryApplication.DictionaryCommandLine.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddViewController implements Initializable {
    private Dictionary dic = new Dictionary();
    private DictionaryManagement dicManagement = new DictionaryManagement();
    private Alerts alerts = new Alerts();
    private String path = "Assets/test.txt";
    @FXML
    private Button addButton;
    @FXML
    private TextField wordTargetType;
    @FXML
    private TextArea wordExplainType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dicManagement.insertFromFile(dic, path);

        if (wordTargetType.getText().isEmpty() && wordExplainType.getText().isEmpty()) {
            addButton.setDisable(true);
        }
        wordTargetType.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!wordTargetType.getText().isEmpty() && !wordExplainType.getText().isEmpty()) {
                    addButton.setDisable(false);
                } else {
                    addButton.setDisable(true);
                }
            }
        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickAddButton();
            }
        });

        wordExplainType.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!wordTargetType.getText().isEmpty() && !wordExplainType.getText().isEmpty()) {
                    addButton.setDisable(false);
                } else {
                    addButton.setDisable(true);
                }
            }

        });

    }

    private void clickAddButton() {
        String word_target = this.wordTargetType.getText().trim();
        String word_explain = wordExplainType.getText().trim();
        Alert alertsConfirmation = alerts.alertsConfirmation("Alert", "Add word ?");
        Optional<ButtonType> optional = alertsConfirmation.showAndWait();

        if (optional.get() == ButtonType.OK) {
            if (dicManagement.searchWord(dic, word_target) != -1) {
                Alert selectionAlert = alerts.alertsConfirmation("Noti", "This word is exised");
                selectionAlert.getButtonTypes().clear();
                ButtonType thaythe = new ButtonType("Change");
                ButtonType bosung = new ButtonType("Add");
                selectionAlert.getButtonTypes().addAll(thaythe, bosung, ButtonType.CANCEL);
                Optional<ButtonType> s1 = selectionAlert.showAndWait();
                int index = dicManagement.searchWord(dic, word_target);
                if (s1.get() == thaythe) {
                    dicManagement.changeWord(dic, index, path, word_explain);
                    this.wordTargetType.setText("");
                    wordExplainType.setText("");
                }
                if (s1.get() == bosung) {
                    dicManagement.addupdateWord(dic, index, path, word_explain);
                    this.wordTargetType.setText("");
                    wordExplainType.setText("");
                }
            } else {
                Alert alertsInformation = alerts.alertsInformation("Noti", " Word added");
                Word word = new Word(word_target, word_explain);
                dicManagement.addWord(word, path);
            }
        }
        if (optional.get() == ButtonType.CANCEL) {
            Alert alertsInformation = alerts.alertsInformation("Alert", "Request is declined");
            this.wordTargetType.setText("");
            wordExplainType.setText("");
            this.wordTargetType.setText("");
            wordExplainType.setText("");
        }

    }
}
