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

<title>singleStar</title>
</head>
<body>
	<jsp:include page="FRAGMENT/header.jsp" />
	<div class="container-non-responsive FX-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Star Details:</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-4 FX-BlackText" align="right">
						<a href=""> <img
							src="http://ia.imdb.com/media/imdb/01/I/57/21/01m.jpg"
							alt="Generic placeholder image" style="height: 250px">
						</a>
					</div>
					<div class="col-xs-6 FX-BlackText">
						<table class="table table-borderless">
							<thead>
								<td style=""><h4>Michael Jackson</h4></td>
							</thead>
							<tbody>
								<tr>
									<td style="">Date of Birth:</td>
									<td>1958-04-21</td>
								</tr>
								<tr>
									<td style="border-top: 0px">Star id:</td>
									<td style="border-top: 0px">430</td>
								</tr>
								<tr>
									<td style="border-top: 0px">Starred in:</td>
									<td style="border-top: 0px"><a href="">This is it</a>,<a
										href="">This is it</a>,<a href="">This is it</a>,<a href="">This
											is it</a></td>
								</tr>
							</tbody>
						</table>
					</div>



				</div>
			</div>
		</div>


	</div>

	<%@ include file="FRAGMENT/footer.html"%>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!--BS's js won't work if we don't include the above one-->
	<script src="CSS/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>