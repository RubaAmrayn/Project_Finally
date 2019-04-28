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
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
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
		String Title= request.getParameter("Title");//taken data from client
		DB_pages code = new DB_pages();
		
		  try {
			 
			  String sql = "SELECT * FROM codes where Title=? and permition='Public'"; //query
			  code.connect();
			  code.statement = code.connect().prepareStatement(sql);
			  code.statement.setString(1, Title);//send to database
			  code.result = code.statement.executeQuery();
			  StringBuilder json = new StringBuilder();//retrieve  data from the database
			  json.append("{ \"codes\": [ ");
			  while(code.result.next()) {
				 if(code.result.isLast()){
				  json.append("{ \"Code_id\":\""+code.result.getString("Code_id")+"\",   ");
				  json.append("\"Title\":\""+code.result.getString("Title")+"\",   ");
				  json.append("\"Content\":\""+code.result.getString("Content")+"\",   ");
				  json.append("\"Code_desc\":\""+code.result.getString("Code_desc")+"\",   ");
				  json.append("\"Create_Date\":\""+code.result.getString("Create_Date")+"\",   ");
				  json.append("\"Language\":\""+code.result.getString("Language")+"\",   ");
				  json.append("\"permition\":\""+code.result.getString("permition")+"\" }  ");
				 }else {
					 json.append("{ \"Code_id\":\""+code.result.getString("Code_id")+"\",   ");
					  json.append("\"Title\":\""+code.result.getString("Title")+"\",   ");
					  json.append("\"Content\":\""+code.result.getString("Content")+"\",   ");
					  json.append("\"Code_desc\":\""+code.result.getString("Code_desc")+"\",   ");
					  json.append("\"Create_Date\":\""+code.result.getString("Create_Date")+"\",   ");
					  json.append("\"Language\":\""+code.result.getString("Language")+"\",   ");
					  json.append("\"permition\":\""+code.result.getString("permition")+"\" },  ");
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


