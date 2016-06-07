
<%
	String content = (String) session.getAttribute("new_star_id");
	session.removeAttribute("new_star_id");
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
<script>
	var content =
<%=content%>
	;
</script>

<title>Insert Star Success</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("../FRAGMENT/e_header.jsp");
	</script>

	<div class="container-non-responsive FX-body">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Insert Star Success</h2>
			</div>
			<div class="panel-body">
				<center class="text-warning" id="result"></center>
			</div>
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("../FRAGMENT/footer.html");
		$('#result').text('Insert Star Success! New star ID: ' + content);
	</script>
</body>
</html>