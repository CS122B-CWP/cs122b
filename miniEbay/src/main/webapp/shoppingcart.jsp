
<%
	String content = (String) session.getAttribute("cartPage");
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
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--BS's js won't work if we don't include the above one-->
<script src="/miniEbay/CSS/bootstrap/js/bootstrap.min.js"></script>
<script>
	var content =
<%=content%>
	;
</script>
<title>shoppingcart</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("/miniEbay/FRAGMENT/header.jsp");
	</script>
	<div class="container-non-responsive FX-body">
		<div class="panel panel-default">
			<div class="panel-heading FX-BrowseText">
				<h3>Shopping Cart:</h3>
			</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<td class="col-xs-5"><h4>Title</h4></td>
							<td class="col-xs-2"><h4>Price</h4></td>
							<td class="col-xs-1"><h4></h4></td>
							<td class="col-xs-1"><h4></h4></td>
						</tr>
					</thead>
					<tbody id="pageContent">
					</tbody>
				</table>
			</div>

		</div>
		<div class="row">
			<div class="col-xs-3 FX-BlackText col-xs-offset-6">
				<h3>Grand Total:</h3>
			</div>
			<div class="col-xs-3 FX-BlackText">
				<h3 id="total_price" class="text-danger"></h3>
			</div>
		</div>

		<div class="row" align="right">
			<button type="button" id="checkBtn" class="btn btn-success btn-lg">Check
				Out</button>
			<button type="button" id="empBtn" class="btn btn-danger btn-lg">Empty
				Cart</button>
		</div>
	</div>
	<div id="footer"></div>
	<script>
		$("#footer").load("/miniEbay/FRAGMENT/footer.html");
		$("#empBtn").click(function() {
			//form
			var _f = $('<form></form>');
			_f.attr('method', 'POST');
			_f.attr('action', 'shoppingcart');

			//type
			var type = $('<input></input>');
			type.attr('name', 'type');
			type.val('removeAll');
			type.appendTo(_f);

			_f.submit();
		});
		$('#checkBtn').click(function() {
			if ($('#header').find('#itemNum').text() > 0) {
				//window.location.href = 'orderInfo.html';
			} else
				alert('Your shopping cart is empty!');
		});
	</script>
	<script>
		var total_price = 0;
		var items = content.items;
		for (i = 0; i < items.length; i++) {
			total_price += items[i].price;
			var tr = $('<tr></tr>');
			var name_td = $('<td></td>');
			name_td.attr('class', 'col-xs-5');
			var a0 = $('<a></a>');
			a0.attr('href', 'singleitem?item_id=' + items[i].item_id);
			a0.text(items[i].title);
			a0.appendTo(name_td);
			name_td.appendTo(tr);

			var price = $('<td></td>');
			price.attr('class', 'col-xs-2');
			price.text('$' + items[i].price);
			price.appendTo(tr);

			var remove = $('<td></td>');
			remove.attr('class', 'col-xs-1');
			var removeBtn = $('<button></button>');
			removeBtn.attr('type', 'button');
			removeBtn.attr('class', 'btn btn-danger btn-xs');
			removeBtn.text('Remove');
			removeBtn.click(function() {
				var values = $(this).parent().parent().children();
				var idtr = values.siblings('.col-xs-5');
				var idVal = idtr.children().attr('href').split('=')[1];
				//form
				var _f = $('<form></form>');
				_f.attr('method', 'POST');
				_f.attr('action', 'shoppingcart');

				//type
				var type = $('<input></input>');
				type.attr('name', 'type');
				type.val('removeSingle');
				type.appendTo(_f);

				//movie_id
				var movie_id = $('<input></input>');
				movie_id.attr('name', 'item_id');
				movie_id.val(idVal);
				movie_id.appendTo(_f);
				_f.submit();
			});
			removeBtn.appendTo(remove);
			remove.appendTo(tr);

			tr.appendTo('#pageContent');
		}

		$('#total_price').text("$" + total_price.toFixed(2));
	</script>
</body>
</html>