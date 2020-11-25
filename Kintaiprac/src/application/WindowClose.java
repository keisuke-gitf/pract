package application;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;
public class WindowClose {

	public void Wclose(ActionEvent eve) {
	Scene s = ((Node)eve.getSource()).getScene();
	Window window = s.getWindow();
	window.hide();

	}

}
