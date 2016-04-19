
<%
	String content = (String) session.getAttribute("searchPage");
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
<title>Search</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("FRAGMENT/header.jsp");
	</script>

	<div class="container FX-body">
		<div class="FX-search">
			<form action="search" method="GET" class="search" role="form">
				<div class="input-group">
					<input autofocus class="form-control input-lg" name="title"
						id="searchT" placeholder="Title" tabindex="1" type="text" value="" />
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-search"></span>
					</span>
				</div>
				<div class="row FX-upPadding">
					<div class="text-center">
						<button class="btn btn-primary FX-HorSpace" name="normal"
							type="submit" tabindex="2">
							<strong>Search</strong>
						</button>
						<button class="btn btn-primary FX-HorSpace" type="submit"
							tabindex="3">
							<strong>Reg Search</strong>
						</button>
					</div>
				</div>
				<a style="cursor: pointer" data-toggle="collapse"
					data-target="#Advanced">Advanced</a>
				<div id="Advanced" class="collapse">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2">Year:</label>
							<div class="col-sm-10">
								<div class="form-group form-inline">
									<input class="form-control input-sm FX-shortInput" name="syear"
										placeholder="YYYY" type="text" value="" maxlength="4" /> - <input
										class="form-control input-sm FX-shortInput" name="eyear"
										placeholder="YYYY" type="text" value="" maxlength="4" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Director:</label>
							<div class="col-sm-10">
								<div class="form-group form-inline">
									<input class="form-control input-sm FX-largeInput"
										name="director" type="text" value="" maxlength="255" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Star:</label>
							<div class="col-sm-10">
								<div class="form-group form-inline">
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1">First
											Name</span> <input class="form-control input-sm FX-midInput"
											name="fname" type="text" value="" maxlength="255" />
									</div>
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1">Last
											Name</span> <input class="form-control input-sm FX-midInput"
											name="lname" type="text" value="" maxlength="255" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<hr>
		<div>
			<ul id="pageContent" class="list-unstyled list-special row FX-row">
			</ul>
			<jsp:include page="FRAGMENT/searchpagination.jsp" />
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("FRAGMENT/footer.html");
	</script>
	<script>
		$('#searchT').val(content.title);
		var movies = content.movies;
		for (i = 0; i < movies.length; i++) {
			var div1 = $('<div></div>');
			div1.attr('class', 'col-md-6 FX-col');
			var li = $('<li></li>');
			li.attr('class', 'list-group-item FX-listItem pre');
			var div2 = $('<div></div>');
			div2.attr('class', 'FX-innerItem FX-listLeft');
			var a0 = $('<a></a>');
			a0.attr('href', 'singlemovie?id=' + movies[i].id);
			var img = $('<img></img>');
			img.attr('src', movies[i].banner_url);
			img.attr('alt', movies[i].title);
			img.attr('style', 'height: 200px; width: 100%;');
			var p0 = $('<p></p>');
			var p1 = $('<p></p>');
			p1.text(movies[i].title);
			img.appendTo(a0);
			p0.appendTo(a0);
			p1.appendTo(a0);
			a0.appendTo(div2);
			var p2 = $('<p></p>');
			p2.text(movies[i].dirctor);
			p2.appendTo(div2);
			div2.appendTo(li);
			var div3 = $('<li></li>');
			div3.attr('class', 'FX-innerItem FX-listRight');
			var hr1 = $('<hr></hr>');
			hr1.text('Year:\t');
			var a1 = $('<a></a>');
			a1.attr('href', 'browse?year=' + movies[i].year);
			a1.text(movies[i].year);
			a1.appendTo(hr1);
			hr1.appendTo(div3);
			var hr2 = $('<hr></hr>');
			hr2.text('Genre:\t');
			var a2 = $('<a></a>');
			a2.attr('href', 'browse?genre=' + movies[i].genre);
			a2.text(movies[i].genre);
			a2.appendTo(hr2);
			hr2.appendTo(div3);
			var hr3 = $('<hr></hr>');
			hr3.text('Stars:\t');
			var stars = movies[i].stars;
			for (j = 0; j < stars.length; j++) {
				var starname = "";
				if (stars[j].fname != null)
					starname += stars[j].fname;
				if (stars[j].lname != null)
					starname += " " + stars[j].lname;
				if (starname != "") {
					var starlink = $('<a></a>');
					starlink.attr('href', 'singlestar?id=' + stars[j].id);
					if (j < stars.length - 1)
						starlink.text(starname + ",\t");
					else
						starlink.text(starname);
					starlink.appendTo(hr3);
				}
			}
			hr3.appendTo(div3);
			div3.appendTo(li);
			li.appendTo(div1);
			div1.appendTo('#pageContent');
		}
	</script>
</body>
</html>