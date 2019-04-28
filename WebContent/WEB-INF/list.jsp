<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" import="myCode.model.user" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<%  
		ArrayList user = (ArrayList) request.getAttribute("users");
		out.println("<table border><tr><th>firstName</th><th>lastName</th><th>username</th><th>Email</th></tr>");
		for(Object obj : user){
		user b = (user) obj;
		String firstName=b.getFirst_name();
		String lastName= b.getLast_name();
		String username= b.getUsername();
		String email=b.getEmail();
		out.println("<tr><td>" + firstName + "</td><td>" + lastName +"</td><td>"+ username +"</td><td>"+ email +"</td></tr>");
		}
		out.println("</table>");
	%>  
</body>
</html>