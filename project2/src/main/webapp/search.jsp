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

<title>Search</title>
</head>
<body>

	<jsp:include page="FRAGMENT/header.jsp" />

	<div class="container FX-body">
		<div class="FX-search">
			<form action="" method="GET" class="search" role="form">
				<div class="input-group">
					<input autofocus class="form-control input-lg" name="username"
						placeholder="Title" tabindex="1" type="text" value="" /> <span
						class="input-group-addon"> <span
						class="glyphicon glyphicon-search"></span>
					</span>
				</div>
				<div class="row FX-upPadding">
					<div class="text-center">
						<button class="btn btn-primary FX-HorSpace" type="submit"
							tabindex="2">
							<strong>Search</strong>
						</button>
						<button class="btn btn-primary FX-HorSpace" type="submit"
							tabindex="3">
							<strong>Lucky</strong>
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
									<input class="form-control input-sm FX-shortInput" name="Year"
										placeholder="YYYY" type="text" value="" maxlength="4" /> - <input
										class="form-control input-sm FX-shortInput" name="Year"
										placeholder="YYYY" type="text" value="" maxlength="4" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="Director">Director:</label>
							<div class="col-sm-10">
								<div class="form-group form-inline">
									<input class="form-control input-sm FX-largeInput"
										name="Director" type="text" value="" maxlength="255" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Star:</label>
							<div class="col-sm-10">
								<div class="form-group form-inline"> 
									<div class="input-group">
									<span class="input-group-addon" id="basic-addon1">First Name</span>
									<input class="form-control input-sm FX-midInput" name="FirstName"
										type="text" value="" maxlength="255" /> 
									</div>	
									<div class="input-group">
									<span class="input-group-addon" id="basic-addon1">Last Name</span>
									<input class="form-control input-sm FX-midInput" name="FirstName"
										type="text" value="" maxlength="255" /> 
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
			<ul class="list-unstyled list-special row FX-row">
				<jsp:include page="FRAGMENT/listItem.jsp" />
				<jsp:include page="FRAGMENT/listItem.jsp" />
				<jsp:include page="FRAGMENT/listItem.jsp" />
				<jsp:include page="FRAGMENT/listItem.jsp" />
				<jsp:include page="FRAGMENT/listItem.jsp" />
			</ul>
			<jsp:include page="FRAGMENT/browsepagination.jsp" />
		</div>
	</div>

	<%@ include file="FRAGMENT/footer.html"%>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!--BS's js won't work if we don't include the above one-->
	<script src="CSS/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>