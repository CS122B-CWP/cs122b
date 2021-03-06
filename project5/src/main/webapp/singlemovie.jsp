
<%
	String content = (String) session.getAttribute("moviePage");
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
<title>singleMovie</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("FRAGMENT/header.jsp");
	</script>
	<div class="container-non-responsive FX-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Movie Details:</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-4 FX-BlackText" align="right">
						<img id="img" style="height: 250px">
					</div>
					<div class="col-xs-6 FX-BlackText" align="right">
						<table class="table">
							<thead>
								<tr>
									<td><h4>
											<I id="title"></I>
										</h4></td>
									<td align="right"><button type="button" id="addBtn"
											class="btn btn-success btn-md">Add to Cart</button></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="">Year:</td>
									<td id="year"></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Director:</td>
									<td id="dirctor" style="border-top: 0px"></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Movie id:</td>
									<td id="movie_id" style="border-top: 0px"></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Stars:</td>
									<td id="stars" style="border-top: 0px"></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Price:</td>
									<td id="price" style="border-top: 0px"></td>
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
		$("#img").attr('src', content.banner_url);
		$("#img").attr('alt', content.title);
		$("#title").text(content.title);
		$("#year").text(content.year);
		$("#dirctor").text(content.dirctor);
		$("#movie_id").text(content.id);
		var stars = content.stars;
		for (i = 0; i < stars.length; i++) {
			var starname = "";
			if (stars[i].fname != null)
				starname += stars[i].fname;
			if (stars[i].lname != null)
				starname += " " + stars[i].lname;
			if (starname != "") {
				var starlink = $('<a></a>');
				starlink.attr('href', 'singlestar?id=' + stars[i].id);
				if (i < stars.length - 1)
					starlink.text(starname + ",\t");
				else
					starlink.text(starname);
				starlink.appendTo("#stars");
			}
		}
		$("#price").text('$' + content.price);
	</script>

	<script>
		$("#addBtn").click(function() {
			var _f = $('<form></form>');
			_f.attr('method', 'POST');
			_f.attr('action', 'shoppingcart');
			var type = $('<input></input>');
			type.attr('name', 'type');
			type.val('add');
			type.appendTo(_f);
			var movie_id = $('<input></input>');
			movie_id.attr('name', 'movie_id');
			movie_id.val($("#movie_id").text());
			movie_id.appendTo(_f);
			var movie_title = $('<input></input>');
			movie_title.attr('name', 'movie_title');
			movie_title.val($("#title").text());
			movie_title.appendTo(_f);
			_f.submit();
		});
	</script>
</body>
</html>