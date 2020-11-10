package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtpassword;

    @FXML
    private Pane file;

    @FXML
    private Label useid;

    @FXML
    private Label pass;

    @FXML
    private TextField txtuserid;

    @FXML
    private Label login;

    @FXML
    private Button btnlogin;

    @FXML
    private Label labelcaution;

    @FXML
    void onActionLogin(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtpassword != null : "fx:id=\"txtpassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert file != null : "fx:id=\"file\" was not injected: check your FXML file 'Login.fxml'.";
        assert useid != null : "fx:id=\"useid\" was not injected: check your FXML file 'Login.fxml'.";
        assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtuserid != null : "fx:id=\"txtuserid\" was not injected: check your FXML file 'Login.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnlogin != null : "fx:id=\"btnlogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert labelcaution != null : "fx:id=\"labelcaution\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
