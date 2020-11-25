package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class stamp extends UserData{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblSyukkin;

    @FXML
    private Button btnLogout;

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
    

    
    private final ObservableList<String> options = 
    FXCollections.observableArrayList(
    	        "A00342",
    	        "A00435",
    	        "A00012"
    	    );
    @FXML
    private ComboBox<String> cboproject;
        

    @FXML
    void onActionSyukkin(ActionEvent event) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rsUpdate = 0;
		try {
			con = new DbConnect().DBConnection();
			pstmt = con.prepareStatement("select *"
					+ "from t_userjob "
					+ "where t_userjob.User_Code = '" + UserData.ID + "'"
					+ "and t_userjob.User_JobInOut = 'I' "
			        + "and t_userjob.User_jobToday = '" + LocalDate.now().format(formatDDate1()) + "'");
			 rs = pstmt.executeQuery();
			if (rs.next()) {
				new ErrorMessage().call("打刻エラー", "本日はすでに出勤打刻済みです。");
				rs.close();
				pstmt.close();
				con.close();
				return;
			}
			pstmt = con.prepareStatement("insert into t_userjob "
					+ "values ("
					+ "'" + UserData.ID + "',"
					+ "'" + LocalDate.now().format(formatDDate1()) + "',"
			        + "'I',"
			        + "'" + LocalTime.now().format(formatTime()) + "',"
			        + "'null',"
			        + "'" + UserData.sysname + "',"
			        + "'" + LocalDateTime.now().format(formatDDate2()) + "',"
			        + "'" + UserData.sysname + "',"
			        + "'" + LocalDateTime.now().format(formatDDate2()) + "');"

					);
			 rsUpdate = pstmt.executeUpdate();
			if (rsUpdate >= 1) {
				new ErrorMessage().call("打刻結果", "出勤打刻しました。");
				rs.close();
				pstmt.close();
				con.close();
				SyukkinOpen();
			}else {
				new ErrorMessage().call("打刻エラー", "打刻に失敗しました。");
				rs.close();
				pstmt.close();
				con.close();
			}



		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			new ErrorMessage().call("打刻エラー", "打刻に失敗しました。");
			e.printStackTrace();
			rs.close();
			pstmt.close();
			con.close();
		}




    }

    @FXML
    void OnActionTaikin(ActionEvent event) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rsUpdate = 0;
		try {
			con = new DbConnect().DBConnection();
			pstmt = con.prepareStatement("select *"
					+ "from t_userjob "
					+ "where t_userjob.User_Code = '" + UserData.ID + "'"
					+ "and t_userjob.User_JobInOut = 'O' "
			        + "and t_userjob.User_jobToday = '" + LocalDate.now().format(formatDDate1()) + "'");
			 rs = pstmt.executeQuery();
			if (rs.next()) {
				new ErrorMessage().call("打刻エラー", "本日はすでに退勤打刻済みです。");
				rs.close();
				pstmt.close();
				con.close();
				return;
			}
			pstmt = con.prepareStatement("insert into t_userjob "
					+ "values ("
					+ "'" + UserData.ID + "',"
					+ "'" + LocalDate.now().format(formatDDate1()) + "',"
			        + "'O',"
			        + "'" + LocalTime.now().format(formatTime()) + "',"
			        + "'" + cboproject.getValue() + "',"
			        + "'" + UserData.sysname + "',"
			        + "'" + LocalDateTime.now().format(formatDDate2()) + "',"
			        + "'" + UserData.sysname + "',"
			        + "'" + LocalDateTime.now().format(formatDDate2()) + "');"

					);
			 rsUpdate = pstmt.executeUpdate();
			if (rsUpdate >= 1) {
				new ErrorMessage().call("打刻結果", "退勤打刻しました。");
				rs.close();
				pstmt.close();
				con.close();
				TaikinOpen();

			}else {
				new ErrorMessage().call("打刻エラー", "打刻に失敗しました。");
				rs.close();
				pstmt.close();
				con.close();
			}


		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			new ErrorMessage().call("打刻エラー", "打刻に失敗しました。");
			e.printStackTrace();
			rs.close();
			pstmt.close();
			con.close();
		}




    }

    @FXML
    void OnActionLogout(ActionEvent event) throws Exception {
       	new loginlogout().Loginflg();
    	new logControl().loginsert("O","S");
    	new WindowClose().Wclose(event);
    	new WindowChange().Wchange("LogOut");
    	UserData.ID = "null";
    	UserData.name = "null";
    	UserData.timer.cancel();
    	UserData.timer.purge();
    }

    private DateTimeFormatter formatDate() {
    	 DateTimeFormatter Dformatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

         return Dformatter;
    }
    private DateTimeFormatter formatDDate1() {
   	 DateTimeFormatter DDformatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return DDformatter1;
   }
    private DateTimeFormatter formatDDate2() {
      	 DateTimeFormatter DDformatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

           return DDformatter2;
      }
    private DateTimeFormatter formatTime() {
   	 DateTimeFormatter Tformatter = DateTimeFormatter.ofPattern("HH:mm");

        return Tformatter;
    }
    private DateTimeFormatter formatSecond() {
      	 DateTimeFormatter Sformatter = DateTimeFormatter.ofPattern("ss");

           return Sformatter;
    }

    private void TimeUpdateschedule() {

    	TimerTask ttask = new TimerTask(){
    		public void run() {
    			UserData.Time = LocalTime.now().format(formatTime());

    	        Platform.runLater(() -> lblTime.setText(UserData.Time));
    		}
    	};
    	int overs = 0;
    	// overs変数に現在の秒を取得
    	overs = Integer.parseInt(LocalTime.now().format(formatSecond()));
        // overs変数に60秒を引いたものを代入
    	overs = 60 - overs;
    	// Timerクラスのオブジェクトを作成
    	UserData.timer = new Timer();

        // 一定間隔で処理を開始する
        // SampleTaskを、0秒後に、60秒間隔で実行する
    	UserData.timer.scheduleAtFixedRate(ttask, overs, 60000);
    }
    //出勤時刻表示
    private void SyukkinOpen() throws SQLException{
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = new DbConnect().DBConnection();
			pstmt = con.prepareStatement("select *"
					+ "from t_userjob "
					+ "where t_userjob.User_Code = '" + UserData.ID + "'"
					+ "and t_userjob.User_JobInOut = 'I' "
			        + "and t_userjob.User_jobToday = '" + LocalDate.now().format(formatDDate1()) + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lblSyukkin.setText(rs.getString("User_JobInOutTime"));
				lblSyukkin.setVisible(true);
				rs.close();
				pstmt.close();
				con.close();
				return;
			}else {
				rs.close();
				pstmt.close();
				con.close();
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			new ErrorMessage().call("表示エラー", "表示に失敗しました。");
			e.printStackTrace();
			rs.close();
			pstmt.close();
			con.close();
		}
    }
    //退勤時刻表示
    private void TaikinOpen() throws SQLException{
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = new DbConnect().DBConnection();
			pstmt = con.prepareStatement("select *"
					+ "from t_userjob "
					+ "where t_userjob.User_Code = '" + UserData.ID + "'"
					+ "and t_userjob.User_JobInOut = 'O' "
			        + "and t_userjob.User_jobToday = '" + LocalDate.now().format(formatDDate1()) + "'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lblTaikin.setText(rs.getString("User_JobInOutTime"));
				lblTaikin.setVisible(true);
				rs.close();
				pstmt.close();
				con.close();
				return;
			}else {
				rs.close();
				pstmt.close();
				con.close();
			}


		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			new ErrorMessage().call("表示エラー", "表示に失敗しました。");
			e.printStackTrace();
			rs.close();
			pstmt.close();
			con.close();
		}
    }

    @FXML
    void initialize() {
    	
        cboproject.setItems(options);
        lblToday.setText(LocalDate.now().format(formatDate()));
    	lblTime.setText(LocalTime.now().format(formatTime()));
    	TimeUpdateschedule();
    	try {
			SyukkinOpen();
			TaikinOpen();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		lblUserName.setText(UserData.name);

		assert lblUserName != null : "fx:id=\"lblUserName\" was not injected: check your FXML file 'stamp.fxml'.";
        assert lblSyukkin != null : "fx:id=\"lblSyukkin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'stamp.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'stamp.fxml'.";
        assert btnTaikin != null : "fx:id=\"btnTaikin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert lblTaikin != null : "fx:id=\"lblTaikin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert btnSyukkin != null : "fx:id=\"btnSyukkin\" was not injected: check your FXML file 'stamp.fxml'.";
        assert lblToday != null : "fx:id=\"lblToday\" was not injected: check your FXML file 'stamp.fxml'.";

    }
}
