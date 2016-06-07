
<%@ page import="miniEbay.jdbc.dao.MetaDAO"%>
<%
	String p_content = MetaDAO.showmetadata();
	//System.out.println(p_content);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../CSS/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../CSS/common1.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--BS's js won't work if we don't include the above one-->
<script src="../CSS/bootstrap/js/bootstrap.min.js"></script>
<title>Main</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("../FRAGMENT/e_header.jsp");
	</script>

	<div class="container FX-body">
		<pre><%=p_content%></pre>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("../FRAGMENT/footer.html");
	</script>
</body>
</html>