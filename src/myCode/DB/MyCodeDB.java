package myCode.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import myCode.model.user;
import java.sql.Statement;
	public class MyCodeDB {
		private static String URL ="db4free.net:3306/myprojectttt";
		private static String user ="rubaproject";
		private static String password ="12345678";
		private Connection connect = null;
		private ResultSet resultSet = null;
		private ArrayList resList = new ArrayList();
	public ArrayList getusers() throws Exception {
	try {
		connect = DriverManager.getConnection("jdbc:mysql://"+URL,user,password);
	Statement statement = connect.createStatement();
	String sql ="select * from users";
	resultSet = statement.executeQuery(sql);
	while (resultSet.next()) {
		
		String firstName = resultSet.getString("First_Name");
		String lastName = resultSet.getString("Last_Name");
		String username = resultSet.getString("username");
		String Email = resultSet.getString("Email");
		resList.add(new user(firstName, lastName,username,Email));
	}
	return resList; }
	catch (Exception e) {
	throw e; } finally { close();
	}}
	private void close() {
	try {
	if (resultSet != null) {
	resultSet.close();
	}
	if (connect != null) {
	connect.close();
	}
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	}
