package application;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WindowChange {



	public void Wchange(String move) throws IOException {
		//ログイン遷移
	if (move == "LogIn") {

	Parent parent = FXMLLoader.load(getClass().getResource("stamp.fxml"));
	Scene scene = new Scene(parent);

	Stage stage = new Stage();
	stage.setScene(scene);
	stage.setTitle("打刻画面");
	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	    @Override
	    public void handle(WindowEvent event) {
	    try{

			new loginlogout().Loginflg();
			new logControl().loginsert("O","S");

	    } catch (Exception e) {
			// TODO 自動生成された catch ブロック
			new ErrorMessage().call("更新エラー", "更新に失敗しました。");
			e.printStackTrace();

		}
	    	UserData.timer.cancel();
	    	UserData.timer.purge();
	    }
	});
    stage.show();

     //ログアウト遷移
	}else if(move == "LogOut"){
		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(parent);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("ログイン画面");
	    stage.show();
	}
}
}

