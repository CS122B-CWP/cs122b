
<%
	String name = (String) session.getAttribute("login_name");
	int total = -1;
	if (name != null)
		total = (int) session.getAttribute("item_nums");
%>
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
				<li><a href="main.jsp" title="Home"> <span
						class="glyphicon glyphicon-home"></span>
				</a></li>
				<li><a href="search.jsp" title="Search"> <span
						class="glyphicon glyphicon-search"></span>
				</a></li>
				<li><a href="browse" title="Browse"> <span
						class="glyphicon glyphicon-film"></span>
				</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><span class="pre text-center FX-HeaderText" id="username"></span></li>
				<li class="dropdown"><a title="Profile" class="dropdown-toggle"
					role="button" aria-haspopup="true" aria-expanded="false"> <span
						class="glyphicon glyphicon-th-large"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="userInfo.jsp" class="pre">User Info</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="orderhistory">Order History</a></li>
					</ul></li>
				<li><a href="shoppingcart" title="Shopping cart"> <span
						class="glyphicon glyphicon-shopping-cart"></span> <span
						class="badge FX-verticalTop" id="itemNum"></span>
				</a></li>
				<li id="login"><a href="login.jsp" title="Login"> <span
						class="glyphicon glyphicon-log-in"> </span>
				</a></li>
				<li id="logout"><a href="logout.jsp" title="Logout"> <span
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
</script>