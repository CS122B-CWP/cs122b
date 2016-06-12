
<%
	String content = (String) session.getAttribute("searchPage");
	session.removeAttribute("searchPage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="/miniEbay/CSS/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/miniEbay/CSS/common1.css">
<link rel="stylesheet" href="/miniEbay/CSS/bootstrap3-typeahead.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--BS's js won't work if we don't include the above one-->
<script src="/miniEbay/CSS/bootstrap/js/bootstrap.min.js"></script>
<script src="/miniEbay/Scripts/typeahead.bundle.js"></script>
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
		$("#header").load("/miniEbay/FRAGMENT/header.jsp");
	</script>

	<div class="container FX-body">
		<div class="FX-search">
			<form action="/miniEbay/search" method="GET" class="search"
				role="form">
				<div class="input-group">
					<input autofocus class="form-control input-lg" name="title"
						id="searchT" placeholder="Title" tabindex="1" type="text" value="" />
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-search"></span>
					</span>
				</div>
				<div class="row FX-upPadding">
					<div class="text-center">
						<button class="btn btn-primary FX-HorSpace" type="submit"
							tabindex="2">
							<strong>Search</strong>
						</button>
					</div>
				</div>
				<a style="cursor: pointer" data-toggle="collapse"
					data-target="#Advanced">Advanced</a>
				<div id="Advanced" class="collapse">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2">Price:</label>
							<div class="col-sm-10">
								<div class="form-group form-inline">
									<input class="form-control input-sm FX-shortInput"
										name="lowPrice" placeholder="low" type="text" value=""
										maxlength="4" /> - <input
										class="form-control input-sm FX-shortInput" name="highPrice"
										placeholder="high" type="text" value="" maxlength="4" />
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
		$("#footer").load("/miniEbay/FRAGMENT/footer.html");
	</script>
	<script>
		var items = content.brief_items;
		for (i = 0; i < items.length; i++) {
			var div1 = $('<div></div>');
			div1.attr('class', 'col-md-6 FX-col');
			var li = $('<li></li>');
			li.attr('class', 'list-group-item FX-listItem pre');
			var div2 = $('<div></div>');
			div2.attr('class', 'FX-innerItem FX-listLeft');
			var a0 = $('<a></a>');
			a0.attr('href', 'singleitem?item_id=' + items[i].item_id);
			var img = $('<img></img>');
			img.attr('src', items[i].gallery_url);
			img.attr('alt', items[i].title);
			img.attr('style', 'height: 200px; width: 100%;');
			var p0 = $('<p></p>');
			var p1 = $('<p></p>');
			p1.text(items[i].title);
			img.appendTo(a0);
			p0.appendTo(a0);
			p1.appendTo(a0);
			a0.appendTo(div2);
			div2.appendTo(li);
			var div3 = $('<li></li>');
			div3.attr('class', 'FX-innerItem FX-listRight');
			var hr1 = $('<hr></hr>');
			hr1.text('Category:\t');
			var a1 = $('<a></a>');
			a1
					.attr('href', 'browse?search_category_id='
							+ items[i].category_id);
			a1.text(items[i].category_name);
			a1.appendTo(hr1);
			hr1.appendTo(div3);
			var hr2 = $('<hr></hr>');
			hr2.text('Current Price:\t$' + items[i].current_price);
			hr2.appendTo(div3);
			div3.appendTo(li);
			li.appendTo(div1);
			div1.appendTo('#pageContent');
			$('#searchT').val(content.search_title);
		}
	</script>
</body>
</html>