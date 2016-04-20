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

<title>Confirmation</title>
</head>
<body>

	<jsp:include page="FRAGMENT/header.jsp" />

	<div class="container-non-responsive FX-body">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Order Confirmation:</h2>
			</div>
			<div class="panel-body">
				<center class="text-warning">Thank you for your purchase!
					Your products will be shipped as soon as they are ready.</center>
				<center class="text-default">Your account has made the
					following purchases to date (lists all purchases):</center>
				<!-- <div class="row">
					<div class="col-md-2 FX-BlackText"><h4>Sales ID:</h4></div>
					<div class="col-md-2 FX-BlackText"><h4>Purchase Date:</h4></div>
					<div class="col-md-8 FX-BlackText"><h4>Movie Purchased:</h4></div>
				</div>
				<talbe class="table">
					<div class="row">
						<div class="col-md-2 FX-BlackText"><h5>174</h5></div>
						<div class="col-md-2 FX-BlackText"><h5>2016-04-21</h5></div>
						<div class="col-md-8 FX-BlackText"><a href=""><h5>Star War</h5></a></div>
					</div>
				</table> -->
				<div class="col-md-8 col-md-offset-2">
					<table class="table" border="1">
						<thead>
							<th>Sales Id:</th>
							<th>Purchase Date:</th>
							<th style="border-left: 0px">Movie Purchased:</th>
						</thead>
						<tbody>
							<tr>
								<td>174</td>
								<td>2016-04-21</td>
								<td><a href="">Star War</a></td>
							</tr>
							<tr>
								<td>1732</td>
								<td>2016-04-21</td>
								<td><a href="">Star War2</a></td>
							</tr>
						</tbody>
					</table>
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