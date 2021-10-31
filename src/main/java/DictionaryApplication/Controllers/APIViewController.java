package DictionaryApplication.Controllers;


import DictionaryApplication.GoogleTranslateApi;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class APIViewController implements Initializable {
    @FXML
    private TextArea enTranslate, vnTranslate;
    @FXML
    private Button translateButton, enSpeaker, vnSpeaker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (enTranslate.getText().isEmpty() && vnTranslate.getText().isEmpty()) {
            translateButton.setDisable(true);
        }
        enTranslate.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!enTranslate.getText().isEmpty()) {
                    translateButton.setDisable(false);
                } else {
                    translateButton.setDisable(true);
                }
            }
        });

        translateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                translateButtonTapped();
            }
        });
        enSpeaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String speakEnglish = enTranslate.getText().trim();
                System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                Voice voice = VoiceManager.getInstance().getVoice("kevin16");
                if (voice != null) {
                    voice.allocate();
                    voice.speak(speakEnglish);
                } else {
                    throw new IllegalStateException("Cannot find voice: kevin16");
                }

            }
        });
        vnSpeaker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String speakEnglish = vnTranslate.getText().trim();
                System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                Voice voice = VoiceManager.getInstance().getVoice("kevin16");
                if (voice != null) {
                    voice.allocate();
                    voice.speak(speakEnglish);
                } else {
                    throw new IllegalStateException("Cannot find voice: kevin16");
                }
            }
        });

    }

    private void translateButtonTapped() {
        String word_english = enTranslate.getText().trim();
        GoogleTranslateApi googleTranslateApi = new GoogleTranslateApi();
        String word_vn = googleTranslateApi.google_Translate("vi", word_english);
        vnTranslate.setText(word_vn);
    }


}
