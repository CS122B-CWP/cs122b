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
<title>Main</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("FRAGMENT/header.jsp");
	</script>

	<div class="container FX-body">
		<div id="FX-recommend-carousel" class="carousel slide"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#FX-recommend-carousel" data-slide-to="0"
					class="active"></li>
				<li data-target="#FX-recommend-carousel" data-slide-to="1"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<div class="span7 text-center pre">
						<a href=""> <img
							src="http://ia.imdb.com/media/imdb/01/I/95/71/38m.jpg"
							alt="Generic placeholder image" style="height: 350px">
						</a>
					</div>
				</div>
				<div class="item">
					<div class="span7 text-center pre">
						<a href=""> <img
							src="http://ia.imdb.com/media/imdb/01/I/57/21/01m.jpg"
							alt="Generic placeholder image" style="height: 350px">
						</a>
					</div>
				</div>

			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#FX-recommend-carousel"
				role="button" data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#FX-recommend-carousel"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>

		<div class="row">
			<div class="col-md-6 FX-centralArea">
				<a href="search.jsp"> <span
					class="glyphicon glyphicon-search FX-browseIcon"></span><br>
					Search
				</a>
			</div>
			<div class="col-md-6 FX-centralArea">
				<a href="browse"> <span
					class="glyphicon glyphicon-film FX-browseIcon"></span><br>
					Browse
				</a>
			</div>
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("FRAGMENT/footer.html");
	</script>
</body>
</html>