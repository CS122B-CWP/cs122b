
<%
	String content = (String) session.getAttribute("itemPage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="/miniEbay/CSS/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/miniEbay/CSS/common1.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--BS's js won't work if we don't include the above one-->
<script src="/miniEbay/CSS/bootstrap/js/bootstrap.min.js"></script>
<script>
	var content =
<%=content%>
	;
	alert(content);
</script>
<title>item detail</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("/miniEbay/FRAGMENT/header.jsp");
	</script>
	<div class="container-non-responsive FX-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Item Details:</h3>
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
											class="btn btn-success btn-md">Buy It Now</button></td>
								</tr>
								<tr>
									<td>Starting bid: US $0.00-<a>3 bids</a></td>
									<td>Time Left: 1 day</td>
								</tr>
								<tr>
									<td><input name="bidPrice"></td>
									<td align="right">
										<button type="button" id="addBtn"
											class="btn btn-primary btn-md">Place bid</button>
									</td>
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
		<div class="well well-sm" align="left">
			<h4>Comments:</h4>
			<hr>
			<div>
				<p>By user1:</p>
				<p>Comments123....blabla</p>
				<hr>
				<p>By user2:</p>
				<p>Comments123....blabla</p>
			</div>
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("/miniEbay/FRAGMENT/footer.html");
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