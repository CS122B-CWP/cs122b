
<%
	String content = (String) session.getAttribute("browsePage");
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
	//var content = JSON.parse(contentstr);
	//console.log(content.movies);
	//console.log(content.movies.length);
	//console.log(content.movies[0].id);
</script>
<title>browse</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("FRAGMENT/header.jsp");
	</script>

	<div class="container FX-body">
		<div class="panel panel-default">
			<div class="panel-heading">Browse</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-2">
						<h3>Genre:</h3>
					</div>
					<div class="col-md-10 FX-BrowseText">
						<span class="glyphicon glyphicon-chevron-right FX-BrowsevAdj"></span>
						<a href="browse?genre=Drama">Drama</a>, <a
							href="browse?genre=Action">Action</a>, <a
							href="browse?genre=Comedy">Comedy</a>, <a
							href="browse?genre=Thriller">Thriller</a>, <a
							href="browse?genre=Adventure">Adventure</a>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-2">
						<h3>Dict:</h3>
					</div>
					<div class="col-md-10 FX-BrowseText">
						<span class="glyphicon glyphicon-chevron-right FX-BrowsevAdj"></span>
						<a href="browse?year=2001">2001</a>, <a href="browse?year=2002">2002</a>,
						<a href="browse?year=2003">2003</a>, <a href="browse?year=2004">2004</a>,
						<a href="browse?year=2005">2005</a>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div>
			<ul id="pageContent" class="list-group list-special row FX-row">
			</ul>
			<jsp:include page="FRAGMENT/browsepagination.jsp" />
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("FRAGMENT/footer.html");
	</script>
	<script>
		var movies = content.movies;
		for (i = 0; i < movies.length; i++) {
			var div1 = $('<div></div>');
			div1.attr('class', 'col-md-6 FX-col');
			var li = $('<li></li>');
			li.attr('class', 'list-group-item FX-listItem pre');
			var div2 = $('<div></div>');
			div2.attr('class', 'FX-innerItem FX-listLeft');
			div2.attr('data-poload', 'singlemovieajax?id=' + movies[i].id);
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

	<script>
		$(document).ready(function() {
			$('[data-poload]').hover(function() {
				var e = $(this);
				$.get(e.data('poload'), function(movie_info) {
					movie_info = $.parseJSON(movie_info);
					e.popover({
						placement : 'right',
						trigger : 'hover',
						container : 'body',
						title : 'Movie Title: ' + movie_info.title,
						html : 'true',
						content : ContentMethod(movie_info)
					}).popover('show');
				});
			});
		});

		function ContentMethod(movie_info) {
			var table = $('<table></table>');
			table.attr('class', 'table table-bordered');
			// banner_url
			var banner_tr = $('<tr></tr>');
			var banner_td = $('<td></td>');
			var banner_img = $('<img></img>');
			banner_img.attr('src', movie_info.banner_url);
			banner_img.attr('style', 'height: 150px; width: 150px;');
			banner_img.appendTo(banner_td);
			banner_td.appendTo(banner_tr);
			banner_tr.appendTo(table);

			// dirctor
			var dirctor_tr = $('<tr></tr>');
			var dirctor_td = $('<td>Dirctor: ' + movie_info.dirctor + '</td>');
			dirctor_td.appendTo(dirctor_tr);
			dirctor_tr.appendTo(table);

			// year
			var year_tr = $('<tr></tr>');
			var year_td = $('<td>Year: ' + movie_info.year + '</td>');
			year_td.appendTo(year_tr);
			year_tr.appendTo(table);

			// price
			var price_tr = $('<tr></tr>');
			var price_td = $('<td>Price: ' + movie_info.price + '</td>');
			price_td.appendTo(price_tr);
			price_tr.appendTo(table);
			return table;
		}
	</script>
</body>
</html>