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
 * Servlet implementation class ViewGroup
 */
@WebServlet("/ViewGroup")
public class ViewGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewGroup() {
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
		String Group_id= request.getParameter("Group_id");
		DB_pages code = new DB_pages();
		
		  try {
			 
			  String sql ="SELECT c.Title,s.* FROM `shared_code` S\r\n" + 
			  		"left join codes c\r\n" + 
			  		"on c.Code_id = s.Codes_code_id\r\n" + 
			  		"WHERE s.Groups_Group_id = ?";
			  code.connect();
			  code.statement = code.connect().prepareStatement(sql);
			  code.statement.setString(1, Group_id);
			  code.result = code.statement.executeQuery();
			  StringBuilder json = new StringBuilder();
			  json.append("{ \"Codes\": [ ");
			  while(code.result.next()) {
				 if(code.result.isLast()){
				  json.append("{ \"Groups_Group_id\":\""+code.result.getString("Groups_Group_id")+"\",   ");
				  json.append("\"Title\":\""+code.result.getString("Title")+"\",   ");
				  json.append("\"Codes_Code_id\":\""+code.result.getString("Codes_Code_id")+"\",   ");
				  json.append("\"Share_Date\":\""+code.result.getString("Share_Date")+"\" }  ");
				 }else {
					  json.append("{ \"Groups_Group_id\":\""+code.result.getString("Groups_Group_id")+"\",   ");
					  json.append("\"Title\":\""+code.result.getString("Title")+"\",   ");
					  json.append("\"Codes_Code_id\":\""+code.result.getString("Codes_Code_id")+"\",   ");
					  json.append("\"Share_Date\":\""+code.result.getString("Share_Date")+"\" }, ");
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
