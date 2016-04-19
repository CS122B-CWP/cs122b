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
						<a href=""> <img
							src="http://ia.imdb.com/media/imdb/01/I/57/21/01m.jpg"
							alt="Generic placeholder image" style="height: 250px">
						</a>
					</div>
					<div class="col-xs-6 FX-BlackText" align="right">
						<table class="table">
							<thead>
								<tr>
									<td><h4>
											<I>StarWar</I>
										</h4></td>
									<td align="right"><button type="button"
											class="btn btn-success btn-md">Add to Cart</button></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="">Year:</td>
									<td>2009</td>
								</tr>
								<tr>
									<td style="border-top: 0px">Director:</td>
									<td style="border-top: 0px">Kenny Ortege</td>
								</tr>
								<tr>
									<td style="border-top: 0px">Movie id:</td>
									<td style="border-top: 0px">174</td>
								</tr>
								<tr>
									<td style="border-top: 0px">Stars:</td>
									<td style="border-top: 0px"><a herf="">Michael Johnson</a>,<a
										herf="">Michael Johnson</a>,<a herf="">Michael Johnson</a>,<a
										herf="">Michael Johnson</a></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Price:</td>
									<td style="border-top: 0px">$15.99</td>
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
</body>
</html>