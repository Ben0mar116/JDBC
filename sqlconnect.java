import java.sql.*;
public class sqlconnect {
protected	Connection cxn = null;
protected ResultSet  rst = null;
	sqlconnect() throws SQLException{
		cxn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprjt","root","");

		
			
			
		
	
	}
	

}