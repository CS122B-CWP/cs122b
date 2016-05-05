<%@page import="project2.jdbc.dao.UserDAO"%>
<jsp:useBean id="user" class="project2.jdbc.bean.UserInfoBean"
	scope="page">
	<jsp:setProperty name="user" property="*" />
</jsp:useBean>
<%
	int customer_id = (int) request.getSession().getAttribute("customer_id");
	user = UserDAO.userinfo(customer_id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="CSS/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="CSS/common1.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--BS's js won't work if we don't include the above one-->
<script src="CSS/bootstrap/js/bootstrap.min.js"></script>
<title>Customer Info</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("FRAGMENT/header.jsp");
	</script>

	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Customer Information</h3>
			</div>
			<form method="POST" action="checkout" id="check_out_form">
				<div class="panel-body">
					<div class="list-group">
						<div class="list-group-item">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">Customer
									ID:</span> <span class="input-group-addon" id="basic-addon5"> <%=user.getId()%>
								</span>
							</div>
						</div>
						<div class="list-group-item">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">First
									Name:</span> <span class="input-group-addon" id="basic-addon5">
									<%=user.getFirst_name()%>
								</span>
							</div>
						</div>

						<div class="list-group-item">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">Last
									Name:</span> <span class="input-group-addon" id="basic-addon5">
									<%=user.getLast_name()%>
								</span>
							</div>
						</div>
						<div class="list-group-item">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">Address:</span>
								<span class="input-group-addon" id="basic-addon5"><%=user.getAddress()%></span>
							</div>
						</div>
						<div class="list-group-item">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">Email:</span>
								<span class="input-group-addon" id="basic-addon5"><%=user.getEmail()%>
								</span>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("FRAGMENT/footer.html");
	</script>

	<script>
		
	</script>
</body>
</html>

