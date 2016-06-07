
<%
	String content = (String) session.getAttribute("historySinglePage");
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
<title>Order Detail</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("FRAGMENT/header.jsp");
	</script>

	<div class="container-non-responsive FX-body">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Order Detail</h2>
			</div>
			<div class="panel-body">
				<div class="col-md-8 col-md-offset-2">
					<table class="table" border="1">
						<thead>
							<th>Quantities</th>
							<th>Unit Price</th>
							<th style="border-left: 0px">Movie Name</th>
						</thead>
						<tbody id='pageContent'>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("FRAGMENT/footer.html");
	</script>
	<script>
		var orders_day = content.orders_day;
		for (i = 0; i < orders_day.length; i++) {
			var tr = $('<tr></tr>');
			// qty
			var qty = $('<td></td>');
			qty.text(orders_day[i].qty);
			qty.appendTo(tr);
			// prices
			var unit_price = $('<td></td>');
			unit_price.text('$' + orders_day[i].unit_price);
			unit_price.appendTo(tr);

			// movie name
			var movie = $('<td></td>');
			var a0 = $('<a></a>');
			a0.attr('href', 'singlemovie?id=' + orders_day[i].movie_id);
			a0.text(orders_day[i].title);
			a0.appendTo(movie);
			movie.appendTo(tr);

			tr.appendTo($('#pageContent'));
		}
	</script>
</body>
</html>