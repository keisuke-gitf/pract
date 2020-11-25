package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorMessage {

	Alert error = new Alert(AlertType.CONFIRMATION);

	public void call(String title, String header) {
		error.setTitle(title);
		error.setHeaderText(header);
		error.showAndWait();
	}


}
