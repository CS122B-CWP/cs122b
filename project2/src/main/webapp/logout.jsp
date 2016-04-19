
<%
	session.removeAttribute("login_name");
	response.sendRedirect("login.jsp");
%>
