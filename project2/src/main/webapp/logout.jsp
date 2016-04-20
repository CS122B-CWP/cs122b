
<%
	session.removeAttribute("login_name");
	session.removeAttribute("customer_id");
	session.removeAttribute("origin_url");
	session.removeAttribute("item_nums");
	response.sendRedirect("login.jsp");
%>
