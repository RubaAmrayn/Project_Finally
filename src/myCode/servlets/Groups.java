package myCode.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myCode.DB.DB_pages;

/**
 * Servlet implementation class Groups
 */
@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Groups() {
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
		String user_id= request.getParameter("user_id");
		DB_pages code = new DB_pages();
		
		  try {
			 
			  String sql = "SELECT g.Name,gm.*,u.username As 'admin' FROM `group_members` gm left join groups g on g.Group_id = gm.Groups_Group_id left join users u on u.user_id = g.Users_user_id WHERE gm.Users_User_id = ?\r\n";
			  code.connect();
			  code.statement = code.connect().prepareStatement(sql);
			  code.statement.setString(1, user_id);
			  code.result = code.statement.executeQuery();
			  StringBuilder json = new StringBuilder();
			  json.append("{ \"Groups\": [ ");
			  while(code.result.next()) {
				 if(code.result.isLast()){
				  json.append("{ \"Group_id\":\""+code.result.getString("Groups_Group_id")+"\",   ");
				  json.append("\"Name\":\""+code.result.getString("Name")+"\",   ");
				  json.append("\"admin\":\""+code.result.getString("admin")+"\",   ");
				  json.append("\"Enroll_Date\":\""+code.result.getString("Enroll_Date")+"\" }  ");
				 }else {
					 json.append("{ \"Group_id\":\""+code.result.getString("Groups_Group_id")+"\",   ");
					  json.append("\"Name\":\""+code.result.getString("Name")+"\",   ");
					  json.append("\"admin\":\""+code.result.getString("admin")+"\",   ");
					  json.append("\"Enroll_Date\":\""+code.result.getString("Enroll_Date")+"\" },  ");
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
