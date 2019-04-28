package myCode.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myCode.DB.DB_pages;

/**
 * Servlet implementation class NewCode
 */
@WebServlet("/NewCode")
public class NewCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private int generateCode() {
		Random rand = new Random(); 
		int value = rand.nextInt(1000);
		return value;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Code_id = Integer.toString(generateCode());
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		 LocalDateTime Create_Date = LocalDateTime.now();
		String Content = request.getParameter("Content");//it is taken from the client
        String Language = request.getParameter("Language");
        String Code_desc = request.getParameter("Code_desc");
        String Title = request.getParameter("Title");
        String permition = request.getParameter("permition"); 
        String Users_user_id= request.getParameter("Users_user_id");
        DB_pages code = new DB_pages();
        String sql = "INSERT INTO codes (Code_id,Title,Content,Code_desc,Create_Date,Language,permition,Users_user_id) values(?,?,?,?,?,?,?,?)";//insert query
		  int r=0;
		  try {
		  code.connect();
		  code.statement = code.connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		  code.statement.setString(1, Code_id);
		  code.statement.setString(2, Title);//send data to database
		  code.statement.setString(3, Content);
		  code.statement.setString(4, Code_desc);
		  code.statement.setString(5, Create_Date.toString());
		  code.statement.setString(6, Language);
		  code.statement.setString(7, permition);
		  code.statement.setString(8, Users_user_id);
		  r = code.statement.executeUpdate();//update query
		  ResultSet rs = code.statement.getGeneratedKeys();
			  response.setContentType("application/json");//coordinate output
			  response.getWriter().write("{\"code_id\": "+Code_id+"}");
		  }catch(SQLException ex) {
			  response.getWriter().write(ex.getMessage());
		  }
	}

}
