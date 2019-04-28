package myCode.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myCode.DB.DB_pages;

/**
 * Servlet implementation class NewGroup
 */
@WebServlet("/NewGroup")
public class NewGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		 LocalDateTime Create_Date = LocalDateTime.now();
		String user_id = request.getParameter("user_id");//it is taken from the client
       String Name = request.getParameter("Name");
       
       DB_pages code = new DB_pages();
       String sql = "INSERT INTO groups (Name,Create_date,Users_user_id) values(?,?,?)";//insert query
		  int r=0;
		  try {
		  code.connect();
		  code.statement = code.connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		  code.statement.setString(1, Name);//send data to database
		  code.statement.setString(2, Create_Date.toString());
		  code.statement.setString(3, user_id);
		  r = code.statement.executeUpdate();//update query
		  ResultSet rs = code.statement.getGeneratedKeys();
		  if(rs.next()) {
			  r = rs.getInt(1);
			 //«” ⁄·«„ «÷«›… «·«œ„‰ ⁄÷Ê ›Ì «·„Ã„Ê⁄… Â‰«
			 
			  String sql1= "INSERT INTO group_members (Users_user_id,Groups_Group_id,Enroll_Date) values(?,?,?)";
			  code.statement = code.connect().prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			  code.statement.setString(1, user_id);//send data to database
			  code.statement.setInt(2, r);
			  code.statement.setString(3, Create_Date.toString()); 
			  code.statement.executeUpdate();//update query
			  response.setContentType("application/json");
			  response.getWriter().write("{\"Group_id\": "+r+"}");
		  }
		 
	
		  }catch(SQLException ex) {
			  response.getWriter().write(ex.getMessage());
		  }

	}

}
