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

    <title>customerInfo</title>
</head>
<body>
	<jsp:include page="FRAGMENT/header.jsp"/>

	<div class="container-fluid">
		<div class="panel panel-default">
			<div class="panel-heading"><h3>Order Information</h3></div>
			<div class="panel-body">

			<p class="text-warning">Please fill out all of the following customer information.</p>
				<div class="list-group">
					<div class="list-group-item">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">Card Number:</span>
  							<input type="text" class="form-control" aria-describedby="basic-addon1">
						</div>
					</div>
					<div class="list-group-item">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon2">Expiration Date:</span>
  							<input type="text" class="form-control" placeholder="YYYY-MM-DD"aria-describedby="basic-addon2">
						</div>
					</div>
					<div class="list-group-item">

						<label class="checkbox-inline"><input type="radio" name="optradio" value="">Visa</label>
						<label class="checkbox-inline"><input type="radio" name="optradio" value="">Mastercard</label>
					</div>
					<div class="list-group-item">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon3">First Name:</span>
  							<input type="text" class="form-control" aria-describedby="basic-addon2">
						</div>
					</div>
					<div class="list-group-item">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon4">Last Name:</span>
  							<input type="text" class="form-control" aria-describedby="basic-addon4">
						</div>
					</div>
				</div>


			<div class="row">
				<button type="button" class="btn btn-success btn-md">Check Out</button>
				<button type="button" class="btn btn-default btn-md">Reset Form</button>
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

