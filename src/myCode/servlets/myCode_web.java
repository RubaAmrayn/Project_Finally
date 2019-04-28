package myCode.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myCode.DB.MyCodeDB;

public class myCode_web extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public myCode_web() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	   this.getServletContext().getRequestDispatcher("/WEB-INF/form.jsp").forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		  MyCodeDB DB = new MyCodeDB();
		  ArrayList result=null;
	    try {
	      result=DB.getusers();
	      request.setAttribute("users", result);
	      this.getServletContext().getRequestDispatcher("/WEB-INF/list.jsp").forward(request,  response);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	   
}
