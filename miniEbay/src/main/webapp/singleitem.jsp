
<%
	String content = (String) session.getAttribute("itemPage");
	// System.out.println(content);
	session.removeAttribute("itemPage");
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
<link rel="stylesheet" href="/miniEbay/CSS/TimeCircles.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--BS's js won't work if we don't include the above one-->
<script src="/miniEbay/CSS/bootstrap/js/bootstrap.min.js"></script>
<script src="/miniEbay/Scripts/TimeCircles.js"></script>
<script>
	var content =
<%=content%>
	;
</script>
<title>item detail</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("/miniEbay/FRAGMENT/header.jsp");
	</script>
	<div class="container-non-responsive FX-fluid">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Item Details:</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-4 FX-BlackText" align="right">
						<div id="FX-recommend-carousel" class="carousel slide"
							data-ride="carousel">
							<!-- Indicators -->
							<ol class="carousel-indicators" id='photo_indicator'>
								<li data-target="#FX-recommend-carousel" data-slide-to="0"
									class="active"></li>
							</ol>

							<!-- Wrapper for slides -->
							<div class="carousel-inner" role="listbox" id='photos'>
								<div class="item active">
									<div class="span7 text-center pre">
										<img src="" alt="" style="height: 300px" id='gallery'>
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
					</div>
					<div class="col-xs-6 FX-BlackText" align="right">
						<table class="table">
							<thead>
								<tr>
									<td><h4>
											<I id="title"></I>
										</h4></td>
								</tr>
								<tr>
									<td id='price' class="bg-success"></td>
									<td><a id='bids'></a>
									<td>
								</tr>
								<tr>
									<td><div>
											<I>Time Left:</I>
											<div id="timeleft"
												style="centre width: 500px; height: 125px; padding: 0px; box-sizing: border-box; background-color: #E0E8EF"></div>
										</div></td>
								</tr>
								<tr id='bid_control'>
									<td>$<input name="bidPrice" id='bid_price'></td>
									<td>
										<button type="button" id="bidBtn"
											class="btn btn-primary btn-md">Place bid</button>
									</td>
								</tr>

							</thead>
							<tbody>
								<tr>
									<td style="border-top: 0px">Category:</td>
									<td id="category" style="border-top: 0px"></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Seller:</td>
									<td style="border-top: 0px"><a href='' id="seller"></a></td>
								</tr>
								<tr>
									<td style="border-top: 0px">Condition Description:</td>
									<td id="des" style="border-top: 0px"></td>
								</tr>
							</tbody>
						</table>


					</div>
				</div>

			</div>
		</div>
		<div class="well well-sm" align="left">
			<h4>Comments:</h4>
			<hr>
			<div id='comment_list'></div>
			<table class="table">
				<thead>
					<tr>
						<td><textarea class="form-control" rows="5"
								id="comment_input"></textarea></td>
					</tr>
					<tr>
						<td><button type="button" id="commnetBtn"
								class="btn btn-primary btn-md">Post Comment</button></td>
					</tr>

				</thead>
			</table>
		</div>
	</div>


	<div id="footer"></div>
	<script>
		$("#footer").load("/miniEbay/FRAGMENT/footer.html");
	</script>

	<script>
		var show_content = function(content) {
			$("#title").text(content.Title);
			$("#category").text(content.CategoryName);
			$("#price").text("Current Price:\t$" + content.CurrentPrice);
			$("#seller").text(content.Seller_Id);
			$("#des").text(content.ConditionDescription);
			$('#gallery').attr('src', content.GalleryURL);
			$('#gallery').attr('alt', content.Title);
			$('#bids').text(content.Bids + " bids");

			var photos = content.PictureURL;
			for (i = 0; i < photos.length; i++) {
				var indicator_li = $('<li></li>');
				indicator_li.attr('data-target', '#FX-recommend-carousel');
				indicator_li.attr('data-slide-to', i + 1);
				indicator_li.appendTo('#photo_indicator');

				var photo_item = $('<div></div>');
				photo_item.attr('class', 'item');
				var single_photo = $('<div></div>');
				single_photo.attr('class', 'span7 text-center pre');
				var single_photo_img = $('<img></img>');
				single_photo_img.attr('src', photos[i]);
				single_photo_img.attr('style', 'height: 300px');
				single_photo_img.appendTo(single_photo);
				single_photo.appendTo(photo_item);
				photo_item.appendTo('#photos');
			}

			var comments = content.Comments;
			for (i = 0; i < comments.length; i++) {
				var user = $('<p></p>');
				var user_link = $('<a></a>');
				user_link.attr('href','');
				user_link.text(comments[i].customer_id);
				user_link.appendTo(user);
				user.appendTo('#comment_list');

				var comment_content = $('<p></p>');
				comment_content.text(comments[i].comment);
				comment_content.appendTo('#comment_list');
				
				var post_date=$('<p></p>');
				post_date.text("Post Date: "+comments[i].post_date);
				post_date.appendTo('#comment_list');
				
				var sep = $('<hr></hr>');
				sep.appendTo('#comment_list');

			}

			if (content.Status == ('active')) {
				$("#timeleft").attr('data-date', content.EndTime);
				$("#timeleft").TimeCircles();
			} else {
				$('#bid_control').hide();
				$("#timeleft").text('Ended!');
			}
		}
	</script>

	<script>
		show_content(content);
		$("#bidBtn").click(function() {
			var my_id ="<%=(String) session.getAttribute("customer_id")%>";
			if (my_id == 'null') {
				alert('You must login!');
				return;
			}
			var price = $("#bid_price").val();

			if (price == '') {
				alert('You must input bid price!');
				return;
			}

			if (!price.match('^[0-9]+(\.[0-9]+)?$')) {
				alert('Pleace input a valid price!');
				return;
			}

			if (parseFloat(price) <= content.CurrentPrice) {
				alert('Current price higher than your bid!');
				return;
			}

			$.ajax({
				url : '/miniEbay/bid',
				type : 'post',
				contentType : 'application/x-www-form-urlencoded',
				dataType : 'json',
				data : {
					item_id : content.ItemID,
					bid_price : price
				},
				success : function(data) {
					if (data.bid_result == 'fail')
						alert(data.info);
					else {
						content = data.item_info;
						show_content(content);
					}
				}
			});
		});

		$("#commnetBtn").click(function() {
			var my_id ="<%=(String) session.getAttribute("customer_id")%>";
			if (my_id == 'null') {
				alert('You must login!');
				return;
			}
			var comment = $("#comment_input").val();

			if (comment == '') {
				alert('You must input comment!');
				return;
			}
			$("#comment_input").val('');

			$.ajax({
				url : '/miniEbay/comment',
				type : 'post',
				contentType : 'application/x-www-form-urlencoded',
				dataType : 'json',
				data : {
					item_id : content.ItemID,
					comment : comment
				},
				success : function(data) {
					if (data.comment_result == 'fail')
						alert(data.info);
					else {
						content = data.item_info;
						show_content(content);
					}
				}
			});
		});
	</script>
</body>
</html>