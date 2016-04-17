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

    <title>shoppingcart</title>
</head>
<body>

	<jsp:include page="FRAGMENT/header.jsp"/>
	<div class="container FX-body">
		<p><h3>Shopping Cart:</h3></p>

		<div class="panel panel-default">
			<div class="panel-heading FX-BrowseText">
		  		<div class="row">
					<div class="col-md-6 FX-BrowseText">Movie Title</div>
					<div class="col-md-2 FX-BrowseText">Price</div>
					<div class="col-md-1 FX-BrowseText">Qty</div>
				</div>			
			</div>

			<div class="panel-body">
	  			<table class="table">
	   		 		<div class="row">
						<div class="col-md-6 FX-BlueText"><a href=""><h4>Starwar1</h4></a></div>	
						<div class="col-md-2 FX-BlueText"><h4>$49.95</h4></div>
						<div class="col-md-1 FX-BlackText">
							<div class="input-group input-group-sm"><input type="text" class="form-control input-md"></div>
						</div>
						<div class="col-md-3 FX-BlueText">
							<div class="btn-group" role = "group">
								<button type="button" class="btn btn-default btn-xs">Update</button>
								<button type="button" class="btn btn-danger btn-xs">Remove</button>
							</div>
						</div>
					</div>
				</table>

				<table class="table">
	   		 		<div class="row">
						<div class="col-md-6 FX-BlueText"><a href=""><h4>Starwar2</h4></a></div>	
						<div class="col-md-2 FX-BlueText"><h4>$49.95</h4></div>
						<div class="col-md-1 FX-BlackText">
							<div class="input-group input-group-sm"><input type="text" class="form-control input-md"></div>
						</div>
						<div class="col-md-3 FX-BlueText">
							<div class="btn-group" role = "group">
								<button type="button" class="btn btn-default btn-xs">Update</button>
								<button type="button" class="btn btn-danger btn-xs">Remove</button>
							</div>
						</div>
					</div>
				</table>

				<div class="row">
					<div class="col-md-2 FX-BlackText col-md-offset-8"><h4>Grand Total:$</h4></div>
					<div class="col-md-2 FX-BlackText"><h3><p class="text-danger">59.15</p></h3></div>
				</div>
			</div>
		
		</div>
		<div class="row">
			<div class="col-md-8"></div>
				<button type="button" class="btn btn-success btn-lg">Check Out</button>
				<button type="button" class="btn btn-danger btn-lg">Empty Cart</button>
		</div>

		<div>
			
			<button type="button" class="btn btn-default btn-md"><span class="glyphicon glyphicon-arrow-left FX-BRowseAdj"></span>Back to previous</button>
		</div>
	</div>	
	<%@ include file="FRAGMENT/footer.html" %>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!--BS's js won't work if we don't include the above one-->
	<script src="CSS/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>