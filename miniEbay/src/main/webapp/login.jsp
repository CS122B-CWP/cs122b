
<%
	if (session.getAttribute("customer_id") != null) {
		response.sendRedirect("/miniEbay/main.jsp");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="/miniEbay/CSS/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/miniEbay/CSS/common1.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--BS's js won't work if we don't include the above one-->
<script src="/miniEbay/CSS/bootstrap/js/bootstrap.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<title>Login</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("/miniEbay/FRAGMENT/header.jsp");
	</script>


	<div class="container FX-body">
		<div class="panel panel-default FX-signin">
			<div class="panel-heading">Sign in</div>
			<div class="panel-body">
				<!--Hide if first come into this page TBD-->
				<p class="text-center pre bg-danger">
					<strong>Please Login</strong>
				</p>

				<form action="/miniEbay/login" method="POST" class="login" role="form">
					<p></p>
					<div class="input-group">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-user"></span>
						</span> <input autofocus class="form-control input-lg" name="email"
							placeholder="Email" required tabindex="1" type="text" value="" />
					</div>
					<p></p>
					<div class="input-group">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-lock"></span>
						</span> <input class="form-control input-lg" id="password"
							name="password" placeholder="Password" required tabindex="2"
							type="password" value="">
					</div>
					<p class="checkbox">
						<label><input type="checkbox" name="remember_me"
							tabindex="3">Remember me</label>
					</p>
					<div class="span7 text-center">
						<button class="btn btn-primary" type="submit" tabindex="4">
							<strong>Sign In</strong>
						</button>
					</div>
				</form>
				<hr>
				<div class="span7 text-center pre">
					Don't have an account? Try <a href="">Sign Up</a>
				</div>
			</div>
		</div>
	</div>

	<div id="footer"></div>
	<script>
		$("#footer").load("/miniEbay/FRAGMENT/footer.html");
	</script>

</body>
</html>