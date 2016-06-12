
<%
	String content = (String) session.getAttribute("browsePage");
	session.removeAttribute("browsePage");
	//System.out.print(content);
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
<link rel="stylesheet" href="/miniEbay/CSS/simple-sidebar.css">
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
<title>browse</title>
</head>
<body>
	<div id="header"></div>
	<script>
		$("#header").load("/miniEbay/FRAGMENT/header.jsp");
	</script>
	<div id="wrapper">
		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Category </a></li>
				<li><a href="/miniEbay/browse">ALL</a></li>
				<li><a href='/miniEbay/browse?search_category_id=20081'>Antiques</a></li>
				<li><a href='/miniEbay/browse?search_category_id=550'>Art</a></li>
				<li><a href='/miniEbay/browse?search_category_id=2984'>Baby</a></li>
				<li><a href='/miniEbay/browse?search_category_id=267'>Books</a></li>
				<li><a href='/miniEbay/browse?search_category_id=12576'>Business
						&amp; Industrial</a></li>
				<li><a href='/miniEbay/browse?search_category_id=625'>Cameras
						&amp; Photo</a></li>
				<li><a href='/miniEbay/browse?search_category_id=15032'>Cell
						Phones &amp; Accessories</a></li>
				<li><a href='/miniEbay/browse?search_category_id=11450'>Clothing,
						Shoes &amp; Accessories</a></li>
				<li><a href='/miniEbay/browse?search_category_id=11116'>Coins
						&amp; Paper Money</a></li>
				<li><a href='/miniEbay/browse?search_category_id=1'>Collectibles</a></li>
				<li><a href='/miniEbay/browse?search_category_id=58058'>Computers
						Tablets &amp; Networking</a></li>
				<li><a href='/miniEbay/browse?search_category_id=293'>Consumer
						Electronics</a></li>
				<li><a href='/miniEbay/browse?search_category_id=14339'>Crafts</a></li>
				<li><a href='/miniEbay/browse?search_category_id=237'>Dolls
						&amp; Bears</a></li>
				<li><a href='/miniEbay/browse?search_category_id=11232'>DVDs
						&amp; Movies</a></li>
				<li><a href='/miniEbay/browse?search_category_id=45100'>Entertainment
						Memorabilia</a></li>
				<li><a href='/miniEbay/browse?search_category_id=172008'>Gift
						Cards &amp; Coupons</a></li>
				<li><a href='/miniEbay/browse?search_category_id=26395'>Health
						&amp; Beauty</a></li>
				<li><a href='/miniEbay/browse?search_category_id=11700'>Home
						&amp; Garden</a></li>
				<li><a href='/miniEbay/browse?search_category_id=281'>Jewelry
						&amp; Watches</a></li>
				<li><a href='/miniEbay/browse?search_category_id=11233'>Music</a></li>
				<li><a href='/miniEbay/browse?search_category_id=619'>Musical
						Instruments &amp; Gear</a></li>
				<li><a href='/miniEbay/browse?search_category_id=1281'>Pet
						Supplies</a></li>
				<li><a href='/miniEbay/browse?search_category_id=870'>Pottery
						&amp; Glass</a></li>
				<li><a href='/miniEbay/browse?search_category_id=10542'>Real
						Estate</a></li>
				<li><a href='/miniEbay/browse?search_category_id=316'>Specialty
						Services</a></li>
				<li><a href='/miniEbay/browse?search_category_id=888'>Sporting
						Goods</a></li>
				<li><a href='/miniEbay/browse?search_category_id=64482'>Sports
						Mem, Cards &amp; Fan Shop</a></li>
				<li><a href='/miniEbay/browse?search_category_id=260'>Stamps</a></li>
				<li><a href='/miniEbay/browse?search_category_id=1305'>Tickets
						&amp; Experiences</a></li>
				<li><a href='/miniEbay/browse?search_category_id=220'>Toys
						&amp; Hobbies</a></li>
				<li><a href='/miniEbay/browse?search_category_id=3252'>Travel</a></li>
				<li><a href='/miniEbay/browse?search_category_id=1249'>Video
						Games &amp; Consoles</a></li>
				<li><a href='/miniEbay/browse?search_category_id=99'>Everything
						Else</a></li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->
		<div id="page-content-wrapper">
			<div class="container FX-body">
				<hr>
				<div>
					<ul id="pageContent" class="list-group list-special row FX-row">
					</ul>
					<jsp:include page="FRAGMENT/browsepagination.jsp" />
				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
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
		}
	</script>
</body>
</html>