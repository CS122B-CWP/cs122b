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

    <title>singleMovie</title>
</head>
<body>
	<jsp:include page="FRAGMENT/header.jsp"/>
	<div class="container FX-fluid">
		<div class="panel panel-default">
			<div class="panel-heading"><h3>Movie Details:</h3></div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4 FX-BlackText text-center">
						<a href="">
		      				<img src="http://ia.imdb.com/media/imdb/01/I/57/21/01m.jpg" alt="Generic placeholder image" 
		      				style="height:250px">
		      			</a>
					</div>
					<div class="col-md-6 FX-BlackText">
						<table class="table">
							<thead>
								<tr>
									<td><h4>StarWar</h4></td>
									<td align="right"><button type="button" class="btn btn-success btn-md">Add to Cart</button>	</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="text-left">Year:</td>
									<td>2009</td>
								</tr>
								<tr>
									<td style="text-left">Director:</td>
									<td>Kenny Ortege</td>
								</tr>
								<tr>
									<td style="text-left">Movie id:</td>
									<td>174</td>
								</tr>
								<tr>
									<td style="text-left">Stars:</td>
									<td><a herf="">Michael Johnson</a>,<a herf="">Michael Johnson</a>,<a herf="">Michael Johnson</a>,<a herf="">Michael Johnson</a></td>
								</tr>
								<tr>
									<td style="text-left">Price:</td>
									<td>$15.99</td>
								</tr>
							</tbody>
						</table>
					</div>
					

					
				</div>
			</div>
		</div>


	</div>	

	<%@ include file="FRAGMENT/footer.html" %>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!--BS's js won't work if we don't include the above one-->
	<script src="CSS/bootstrap/js/bootstrap.min.js"></script>	
</body>
</html>