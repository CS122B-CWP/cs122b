
<%
	session.removeAttribute("customer_id");
	session.removeAttribute("origin_url");
	session.removeAttribute("item_nums");
	session.removeAttribute("cartPage");
	session.removeAttribute("orderhistoryPage");
	session.removeAttribute("historySinglePage");
	response.sendRedirect("login.jsp");
%>
