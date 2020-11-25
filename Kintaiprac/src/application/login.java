package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class login extends UserData{

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
	void onActionLogin(ActionEvent event) {

		try (

				Connection con = new DbConnect().DBConnection();
				PreparedStatement pstmt = con.prepareStatement("select m_user.User_Code,m_user.User_Name,m_user.User_Password "
						+ "from m_user "
						+ "where m_user.User_Code = '" + txtuserid.getText() + "'"
						+ "and m_user.User_Password = '" + txtpassword.getText() + "'");

				ResultSet rs = pstmt.executeQuery();


		) {

			if (rs.next()) {
				UserData.ID = rs.getString("User_Code");
				UserData.name = rs.getString("User_Name");

				new loginlogout().Loginflg();
				new logControl().loginsert("I","S");
				new WindowClose().Wclose(event);

				new WindowChange().Wchange("LogIn");
			} else {
				new logControl().loginsert("O","F") ;
				new ErrorMessage().call("ログインエラー", "ログインIDまたはパスワードが違います");
			}


		} catch (SQLException t) {
			t.printStackTrace();
			new ErrorMessage().call("ログインエラー", "データベース接続に失敗しました。");

		} catch (Exception e) {
			e.printStackTrace();
			new ErrorMessage().call("ログインエラー", "処理が失敗しました。");
		}

	}

	@FXML
	void initialize() {
		UserData.sysname = new SystemName().getHostName();
		// 文字数制限
		txtuserid.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			  String str = txtuserid.getText();
			  if (str.length() > 8) {
				  txtuserid.setText(txtuserid.getText().substring(0, txtuserid.getText().length() - 1));
			  }
			});
		// 文字数制限
		txtpassword.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			  String str = txtpassword.getText();
			  if (str.length() > 20) {
				  txtpassword.setText(txtpassword.getText().substring(0, txtpassword.getText().length() - 1));
			  }
			});
		assert txtpassword != null : "fx:id=\"txtpassword\" was not injected: check your FXML file 'Login.fxml'.";
		assert file != null : "fx:id=\"file\" was not injected: check your FXML file 'Login.fxml'.";
		assert useid != null : "fx:id=\"useid\" was not injected: check your FXML file 'Login.fxml'.";
		assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'Login.fxml'.";
		assert txtuserid != null : "fx:id=\"txtuserid\" was not injected: check your FXML file 'Login.fxml'.";
		assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'Login.fxml'.";
		assert btnlogin != null : "fx:id=\"btnlogin\" was not injected: check your FXML file 'Login.fxml'.";

	}
}
