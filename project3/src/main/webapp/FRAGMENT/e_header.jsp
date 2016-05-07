
<%
	String name = (String) session.getAttribute("e_login_name");
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
			<a class="navbar-brand logo-color"> <strong>FABFLIX EMPLOYEE</strong>
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="main.html" title="Dashboard"> <span
						class="glyphicon glyphicon-home"></span>
				</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><span class="pre text-center FX-HeaderText" id="username"></span></li>
				<li class="dropdown"><a title="Operations"
					class="dropdown-toggle" role="button" aria-haspopup="true"
					aria-expanded="false"> <span
						class="glyphicon glyphicon-th-large"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="meta.jsp" class="pre">Show Metadata</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="insert_star.html">Insert Star</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="insert_movie.html">Insert Movie</a></li>
					</ul></li>

				<li id="login"><a href="employee_login.jsp"
					title="Employee Login"> <span
						class="glyphicon glyphicon-log-in"> </span>
				</a></li>
				<li id="logout"><a href="e_logout.jsp" title="Logout"> <span
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
	}
	$("#username").text(name_msg);
</script>