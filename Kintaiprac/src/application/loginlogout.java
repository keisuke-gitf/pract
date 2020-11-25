package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginlogout extends UserData{

	@SuppressWarnings("resource")
	public  void Loginflg() throws Exception{

		Connection con = new DbConnect().DBConnection();
		PreparedStatement pstmt = con.prepareStatement("select m_user.User_LogState "
				+ "from m_user "
		        + "where m_user.User_Code = '" + UserData.ID + "'");

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
		if(rs.getString("User_LogState").equals("O")) {
			 pstmt = con.prepareStatement("update m_user set "
					+ "User_LogState = 'I'"
					+ "where m_user.User_Code = '" + UserData.ID + "'");
	    pstmt.executeUpdate();
		}

		else if(rs.getString("User_LogState").equals("I")) {
			 pstmt = con.prepareStatement("update m_user set "
						+ "User_LogState = 'O'"
						+ "where m_user.User_Code = '" + UserData.ID + "'");

		pstmt.executeUpdate();

		}
		}
		pstmt.close();
	}


}
