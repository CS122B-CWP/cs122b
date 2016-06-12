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
<title>Main</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("/miniEbay/FRAGMENT/header.jsp");
	</script>

	<div class="container FX-body">
		<div class="row">
			<div class="col-md-6 FX-centralArea">
				<a href="/miniEbay/search"> <span
					class="glyphicon glyphicon-search FX-browseIcon"></span><br>
					Search
				</a>
			</div>
			<div class="col-md-6 FX-centralArea">
				<a href="/miniEbay/browse"> <span
					class="glyphicon glyphicon-film FX-browseIcon"></span><br>
					Browse
				</a>
			</div>
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("/miniEbay/FRAGMENT/footer.html");
	</script>
</body>
</html>