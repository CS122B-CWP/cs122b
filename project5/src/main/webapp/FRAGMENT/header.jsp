
<%
	String name = (String) session.getAttribute("login_name");
	int total = -1;
	if (name != null)
		total = (int) session.getAttribute("item_nums");
%>
<script src="/fabflix/Scripts/typeahead.bundle.js"></script>
<link rel="stylesheet" href="/fabflix/CSS/bootstrap3-typeahead.css">
<nav class="navbar navbar-default navbar-static-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!--Href TBD-->
			<a class="navbar-brand logo-color"> <strong>FABFLIX</strong>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="/fabflix/main.jsp" title="Home"> <span
						class="glyphicon glyphicon-home"></span>
				</a></li>
				<li><a href="/fabflix/search.jsp" title="Search Page"> <span
						class="glyphicon glyphicon-search"></span>
				</a></li>
				<li><a href="/fabflix/browse" title="Browse"> <span
						class="glyphicon glyphicon-film"></span>
				</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-middle" id="search_nav">
				<li><input autofocus class="form-control input-lg" name="title"
					id="searchT_h" placeholder="Title" type="text" value="" /></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><span class="pre text-center FX-HeaderText" id="username"></span></li>
				<li class="dropdown"><a title="Profile" class="dropdown-toggle"
					role="button" aria-haspopup="true" aria-expanded="false"> <span
						class="glyphicon glyphicon-th-large"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="/fabflix/userInfo.jsp" class="pre">User Info</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="/fabflix/orderhistory">Order History</a></li>
					</ul></li>
				<li><a href="/fabflix/shoppingcart" title="Shopping cart"> <span
						class="glyphicon glyphicon-shopping-cart"></span> <span
						class="badge FX-verticalTop" id="itemNum"></span>
				</a></li>
				<li id="login"><a href="/fabflix/login.jsp" title="Login"> <span
						class="glyphicon glyphicon-log-in"> </span>
				</a></li>
				<li id="logout"><a href="/fabflix/logout.jsp" title="Logout"> <span
						class="glyphicon glyphicon-log-out"> </span>
				</a></li>
			</ul>
		</div>
	</div>
</nav>

<script>
	$("#logout").hide();
	var name ="<%=name%>";
	if (name == "null" || name == "") {
		var name_msg = "Hi,Guest";
		$("#itemNum").text('');
	} else {
		var name_msg = "Hi," + name;
		$("#logout").show();
		$("#login").hide();
		var total ="<%=total%>";
		if (total != -1)
			$("#itemNum").text(total);
	}
	$("#username").text(name_msg);

	var url = window.location.href;
	if (url.indexOf('search') >= 0)
		$('#search_nav').hide();
	else
		$('#search_nav').show();
</script>
<script>
	var prefixed_titles = "";
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			prefixed_titles = $.parseJSON(xhttp.responseText);
			//substringMatcher();
		}
	};

	$('#searchT_h').bind('input propertychange', function() {
		var prefix = $("#searchT_h").val();
		if (prefix != "") {
			xhttp.open("GET", "searchajax?title=" + prefix, true);
			xhttp.send();
		}
	});

	var matched_titles = function() {
		return function findMatches(q, cb) {
			var matches;

			// an array that will be populated with substring matches
			matches = [];

			// iterate through the pool of strings and for any string that
			// contains the substring `q`, add it to the `matches` array
			$.each(prefixed_titles, function(i, prefixed_titles) {
				matches.push(prefixed_titles);
			});

			cb(matches);
		};
	};

	$('#searchT_h').typeahead({
		hint : true,
		highlight : true,
		minLength : 2
	}, {
		name : 'states',
		source : matched_titles()
	});
</script>