
<%
	String content = (String) session.getAttribute("StarPage");
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
<script>
	var content =
<%=content%>
	;
</script>
<title>singleStar</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("FRAGMENT/header.jsp");
	</script>
	<div class="container-non-responsive FX-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Star Details:</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-4 FX-BlackText" align="right">
						<a href=""> <img id="img" style="height: 250px">
						</a>
					</div>
					<div class="col-xs-6 FX-BlackText">
						<table class="table table-borderless">
							<thead>
								<tr>
									<td><h4 id="name"></h4></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="">Date of Birth:</td>
									<td id="dob"></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Star id:</td>
									<td id="star_id" style="border-top: 0px"></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Starred in:</td>
									<td id="movies" style="border-top: 0px"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer"></div>
	<script>
		$("#footer").load("FRAGMENT/footer.html");
	</script>
	<script>
		$("#img").attr('src', content.photo_url);
		var starname = "";
		if (content.fname != null)
			starname += content.fname;
		if (content.lname != null)
			starname += " " + content.lname;
		$("#img").attr('alt', starname);
		$("#name").text(starname);
		$("#dob").text(content.dob);
		$("#star_id").text(content.id);
		var movies = content.movies;
		for (i = 0; i < movies.length; i++) {
			var movielink = $('<a></a>');
			movielink.attr('href', 'singlemovie?id=' + movies[i].id);
			if (i < movies.length - 1)
				movielink.text(movies[i].title + ",\t");
			else
				movielink.text(movies[i].title);
			movielink.appendTo("#movies");

		}
	</script>
</body>
</html>