package application;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnect {
      private final String DBroot = "jdbc:mysql://localhost:3306/localcdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      private final String	ID = "root";
      private final String Password = "fkeisukesql";

	public Connection DBConnection() throws Exception{

	    Connection con = null;

	    Class.forName("com.mysql.cj.jdbc.Driver");
	      con = DriverManager.getConnection(
	    	DBroot,
	    	ID,
	    	Password
	      );

	      return con;

	    }
}





/*	      while (rs.next()) {
	    	System.out.println(rs.getString("User_Name"));
	        System.out.println(rs.getString("User_Code"));
	      }

	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException e) {
	          e.printStackTrace();
	        }
	      }
	      if (pstmt != null) {
	        try {
	          pstmt.close();
	        } catch (SQLException e) {
	          e.printStackTrace();
	        }
	      }
	      if (con != null) {
	        try {
	          con.close();
	        } catch (SQLException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	}


}*/
