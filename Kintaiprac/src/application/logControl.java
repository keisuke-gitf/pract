package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logControl {

	public  void loginsert(String Cflg ,String Sflg ) throws Exception{
		Connection con = new DbConnect().DBConnection();
		PreparedStatement pstmt = con.prepareStatement("insert into l_login "
				+ "values ("
				+ "'" + UserData.ID + "',"
				+ "'" +UserData.name+ "',"
		        + "'" +Cflg+"',"
		        + "'" +Sflg+ "',"
		        + "'" + UserData.sysname + "',"
		        + "'" + LocalDateTime.now().format(formatDDate2()) + "')");
		         pstmt.executeUpdate();
	}
    private DateTimeFormatter formatDDate2() {
     	 DateTimeFormatter DDformatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

          return DDformatter2;
     }

}
