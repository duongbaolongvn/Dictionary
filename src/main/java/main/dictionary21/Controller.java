package main.dictionary21;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freettes.VoiceManagement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.util.Callback;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
        @FXML
        private ListView<Word> listView;
        @FXML private TextField wordtarget;
//        @FXML private WebView webView;
//        private WebEngine webEngine;
        @FXML private TextArea textArea;
        public List<Word> words = new ArrayList<>();
        public void initialize (URL location, ResourceBundle resoureces ) {

//            webEngine = webView.getEngine();

            listView.setItems(words);
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
//                    webEngine.loadContent(listView.getSelectionModel().getSelectionItem().getWord_explain());
                    textArea.appendText(listView.getSelectionModel().getSelectedItem().getWord_explain());

                }
            });

            wordtarget.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    String target = wordtarget.getText();

                }
            });
        }

        public int binarySearch(String s) {
            int mid, a = 0;
            int b = words.size() - 1 ;
            while( a <= b) {
                mid = a + (a-b)/2;
                int c = words.get(mid).getWord_tagert().compareTo(s);
                if(c > 0) {
                    a = mid - 1;
                }
                else if (c < 0) {
                    a = mid + 1;
                }
                else {
                    return mid;
                }
            }
            return -1;
        }

        public void printTranslate(ActionEvent event) {
            String target = wordtarget.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            if(target.length() == 0) {
                alert.setContentText("Write the words");
                alert.show();
                wordtarget.clear();
            }
            else {
                textArea.appendText(words.get(i).getWord_explain());
                wordtarget.clear();
            }
        }
        public void addWord (ActionEvent event) {
            Dialog<Word> dialog = new Dialog<>();
            dialog.setTitle("Add words");
            dialog.setHeaderText("Write data into blank, press 'x' to cancel");
            dialog.setResizable(true);

            Label label1 = new Label("Word: ");
            Label label2 = new Label("Meaning of word: ");
            TextField text1 = new TextField();
            TextField text2 = new TextField();

            GridPane gridPane = new GridPane();
            gridPane.add(label1, 1,1);
            gridPane.add(label2, 1, 2);
            gridPane.add(text1,2,1);
            gridPane.add(text2,2, 2);

            ButtonType Ok = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(Ok);
            dialog.setResultConverter(new Callback<ButtonType, Word>() {
                @Override
                public Word call(ButtonType button) {

                    if(button == Ok ) {
                        Word newWord = new Word();
                        newWord.setWord_tagert(text1.getText());
                        newWord.setWord_explain( text1.getText() + text2.getText());
                        return newWord;
                    }
                    return  null;
                }
            });

            Optional<Word> result = dialog.showAndWait();
            if(result.isPresent()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                int i =binarySearch(result.get().getWord_tagert());
                if(i != -1) {
                    alert.setContentText("Word was existed");
                    alert.show();
                }
                else {
                    words.add(result.get());
                    Collections.sort(words, new WordComparator());
                    alert.setContentText("Add word sucessfull");
                    alert.show();
                    listView.setItems(words);


                }
            }
        }

        public void deletWord (ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("alert");
            alert.setHeaderText(null);

            if(listView.getSelectionModel().isEmpty()) {
                alert.setContentText("Pls choose word to delete");
                alert.show();
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Accepted");
                alert2.setHeaderText("Delete");
                alert2.setContentText("Are you sure to delete this word? ");
                Optional<ButtonType> results2  = alert2.showAndWait();
                if(results2.get() == ButtonType.OK) {
                    String item = listView.getSelectionModel().getSelectedItem().getWord_target();
                    int i = binarySearch(item);
                    words.remove(i);
                    listView.setItems(words);

                    alert.setContentText("Delete sucessfull!");
                    alert.show();
//                    webEngine.loadCotent("");
                    textArea.appendText(" ");
                    wordtarget.clear();

                }
            }
        }
        public void changeWord (ActionEvent event) {
            Alert noti = new Alert(Alert.AlertType.INFORMATION);
            noti.setTitle("Alert");

            if (listView.getSelectionModel().isEmpty()) {
                noti.setContentText("Pls choose word to change");
                noti.setHeaderText(null);
                noti.show();
            } else {

                Dialog<Word> dialog = new Dialog<>();
                dialog.setTitle("Change Word " + listView.getSelectionModel().getSelectedItem().getWord_target());
                dialog.setHeaderText("Write data into blank, press 'x' to cancel");
                dialog.setResizable(true);

                String s = listView.getSelectionModel().getSelectedItem().getWord_target();
                int i = binarySearch(s);

                Label label1 = new Label("Word: ");
                Label label2 = new Label("Meaning of word: ");
                TextField text1 = new TextField();
                TextField text2 = new TextField();

                GridPane gridPane = new GridPane();
                gridPane.add(label1, 1, 1);
                gridPane.add(label2, 1, 2);
                gridPane.add(text1, 2, 1);
                gridPane.add(text2, 2, 2);
                dialog.getDialogPane().setContent(gridPane);

                ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

                dialog.setResultConverter(new Callback<ButtonType, Word>() {
                    @Override
                    public Word call(ButtonType param) {
                        if (param == buttonTypeOk) {
                            words.get(i).setWord_explain(text1.getText() + text2.getText());
                            return words.get(i);
                        }
                        return null;
                    }
                });

                Optional<Word> result = dialog.showAndWait();
                if (result.isPresent()) {
                    noti.setHeaderText(null);
                    noti.setContentText("Change word successfull");
                    noti.show();
                    listView.setItems(words);
//                    webEngine.loadContent(listView.getSelectionModel().getSelectedItem().getWord_explain());
                    textArea.appendText(listView.getSelectionModel().getSelectedItem().getWord_explain());
                }
            }
        }

        public void spell (ActionEvent event) {

            System.setProperty("mbrola.base", "mbrola");
            Voice voice;
            String speech = listView.getSelectionModel().getSelectedItem().getWord_target();
            voice = VoiceManager.getInstance().getVoice("mbrola_us1");
            voice.allocate();
            voice.speak(speech);
            voice.deallocate();
        }



    }

