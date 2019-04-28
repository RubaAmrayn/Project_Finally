package myCode.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DB_pages {
	                    //«·« ’«· »«·œ« « »Ì”
	   private static String URL="localhost:3306/mycode";
	   private static String user="root";
	   private static String password="";
	   public PreparedStatement statement = null;
	   public ResultSet result= null;
	   public Connection connect() throws SQLException {
		   Connection connect = DriverManager.getConnection("jdbc:mysql://"+URL+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", user, password);
		   
		   return connect;
	   }
	}
