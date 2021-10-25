module main.dictionary21 {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.dictionary21 to javafx.fxml;
    exports main.dictionary21;
}