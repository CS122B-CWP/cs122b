
<%
	String name = (String) session.getAttribute("customer_id");
	int total = -1;
	if (name != null)
		total = (int) session.getAttribute("item_nums");
%>
<script src="/miniEbay/Scripts/typeahead.bundle.js"></script>
<link rel="stylesheet" href="/miniEbay/CSS/bootstrap3-typeahead.css">
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
			<a class="navbar-brand logo-color"> <strong>miniEbay</strong>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="/miniEbay/main.jsp" title="Home"> <span
						class="glyphicon glyphicon-home"></span>
				</a></li>
				<li><a href="/miniEbay/search.jsp" title="Search Page"> <span
						class="glyphicon glyphicon-search"></span>
				</a></li>
				<li><a href="/miniEbay/browse" title="Browse"> <span
						class="glyphicon glyphicon-th-list"></span>
				</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><span class="pre text-center FX-HeaderText" id="username"></span></li>
				<li class="dropdown"><a title="Profile" class="dropdown-toggle"
					role="button" aria-haspopup="true" aria-expanded="false"> <span
						class="glyphicon glyphicon-th-large"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="/miniEbay/userInfo.jsp" class="pre">User
								Info</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="/miniEbay/orderhistory">Order History</a></li>
					</ul></li>
				<li><a href="/miniEbay/shoppingcart" title="Shopping cart">
						<span class="glyphicon glyphicon-shopping-cart"></span> <span
						class="badge FX-verticalTop" id="itemNum"></span>
				</a></li>
				<li id="login"><a href="/miniEbay/login.jsp" title="Login">
						<span class="glyphicon glyphicon-log-in"> </span>
				</a></li>
				<li id="logout"><a href="/miniEbay/logout.jsp" title="Logout">
						<span class="glyphicon glyphicon-log-out"> </span>
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
</script>