package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class stamp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblSyukkin;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnTaikin;

    @FXML
    private Label lblTaikin;

    @FXML
    private Button btnSyukkin;

    @FXML
    private Label lblToday;

    @FXML
    void onActionSyukkin(ActionEvent event) {

    }

    @FXML
    void OnActionTaikin(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lblSyukkin != null : "fx:id=\"lblSyukkin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'stamp.fxml'.";
        assert btnTaikin != null : "fx:id=\"btnTaikin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert lblTaikin != null : "fx:id=\"lblTaikin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert btnSyukkin != null : "fx:id=\"btnSyukkin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert lblToday != null : "fx:id=\"lblToday\" was not injected: check your FXML file 'stamp.fxml'.";

    }
}

