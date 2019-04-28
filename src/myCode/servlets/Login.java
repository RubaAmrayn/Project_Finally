package myCode.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myCode.DB.DB_pages;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.html");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String user = request.getParameter("user");
         String pass = request.getParameter("pass");
		//Login TODO
		
		DB_pages code = new DB_pages();
		
		  try {
			 
			  String sql = "SELECT * FROM users where username=? and password = ?";
			  code.connect();
			  code.statement = code.connect().prepareStatement(sql);
			  code.statement.setString(1, user);
			  code.statement.setString(2, pass);
			  code.result = code.statement.executeQuery();
			  StringBuilder json = new StringBuilder();
			  json.append("{ \"users\": [ ");
			  while(code.result.next()) {
				 if(code.result.isLast()){
				  json.append("{ \"user_id\":\""+code.result.getString("user_id")+"\",   ");
				  json.append("\"username\":\""+code.result.getString("username")+"\",   ");
				  json.append("\"password\":\""+code.result.getString("password")+"\",   ");
				  json.append("\"First_Name\":\""+code.result.getString("First_Name")+"\",   ");
				  json.append("\"Last_Name\":\""+code.result.getString("Last_Name")+"\",   ");
				  json.append("\"Email\":\""+code.result.getString("Email")+"\" }  ");
				 }else {
					 json.append("{ \"user_id\":\""+code.result.getString("user_id")+"\",   ");
					  json.append("\"username\":\""+code.result.getString("username")+"\",   ");
					  json.append("\"password\":\""+code.result.getString("password")+"\",   ");
					  json.append("\"First_Name\":\""+code.result.getString("First_Name")+"\",   ");
					  json.append("\"Last_Name\":\""+code.result.getString("Last_Name")+"\",   ");
					  json.append("\"Email\":\""+code.result.getString("Email")+"\" },  ");
				 }
			  }
			  json.append("]}");
			  response.setContentType("application/json");
		  response.getWriter().write(json.toString());
		  }catch(SQLException ex) {
			  response.getWriter().write(ex.getMessage());
		  }
	}

}
