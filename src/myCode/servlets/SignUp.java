package myCode.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myCode.DB.DB_pages;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.html");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String user = request.getParameter("user");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        
        DB_pages code = new DB_pages();
        String sql = "INSERT INTO users (username,password,First_Name,Last_Name,Email) values(?,?,?,?,?)";
		  int r=0;
		  try {
		  code.connect();
		  code.statement = code.connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		  code.statement.setString(1, user);
		  code.statement.setString(2, pass);
		  code.statement.setString(3, firstName);
		  code.statement.setString(4, lastName);
		  code.statement.setString(5, email);
		  r = code.statement.executeUpdate();
		  ResultSet rs = code.statement.getGeneratedKeys();
		  if(rs.next()) {
			  r = rs.getInt(1);
			  response.setContentType("application/json");
			  response.getWriter().write("{\"user_id\": "+r+"}");
		  }
		  }catch(SQLException ex) {
			  response.getWriter().write(ex.getMessage());
		  }
	 
	}

}
