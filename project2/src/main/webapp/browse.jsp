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

<title>browse</title>
</head>
<body>

	<jsp:include page="FRAGMENT/header.jsp" />

	<div class="container FX-body">
		<div class="panel panel-default">
			<div class="panel-heading">Browse</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-2">
						<h3>Genre:</h3>
					</div>
					<div class="col-md-10 FX-BrowseText">
						<a href=""><kbd>ALL</kbd></a> <span
							class="glyphicon glyphicon-chevron-right FX-BrowsevAdj"></span> <a
							href="">a</a> <a href="">b</a> <a href="">c</a>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-2">
						<h3>Dict:</h3>
					</div>
					<div class="col-md-10 FX-BrowseText">
						<a href=""><kbd>ALL</kbd></a> <span
							class="glyphicon glyphicon-chevron-right FX-BrowsevAdj"></span> <a
							href="">a</a> <a href="">b</a> <a href="">c</a>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div>
		<ul class="list-group list-special row FX-row">
			<jsp:include page="FRAGMENT/listItem.jsp" />
			<jsp:include page="FRAGMENT/listItem.jsp" />
			<jsp:include page="FRAGMENT/listItem.jsp" />
			<jsp:include page="FRAGMENT/listItem.jsp" />
			<jsp:include page="FRAGMENT/listItem.jsp" />
		</ul>
		<jsp:include page="FRAGMENT/pagination.jsp" />
		</div>
	</div>

	<%@ include file="FRAGMENT/footer.html"%>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!--BS's js won't work if we don't include the above one-->
	<script src="CSS/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>