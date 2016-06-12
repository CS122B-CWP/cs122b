
<nav style="text-align: center">
	<ul class="pagination">
		<li id="pageP"><a href="" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<li id="pageN"><a href="" aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>

<script>
	if (content != null) {
		var startP = content.curPage - 2;
		if (startP <= 0)
			startP = 1;
		var endP = content.curPage + 2;
		if (endP < 5)
			endP = 5;
		if (endP > content.maxPage)
			endP = content.maxPage;
		for (i = endP; i >= startP && i > 0; i--) {
			var li = $('<li></li>');
			var a = $('<a></a>');
			var herfStr =
					'search?' + '&page=' + i + '&title=' + content.search_title
							+ '&lowPrice=' + content.lowPrice + '&highPrice='
							+ content.highPrice;
			a.attr('href', herfStr);
			a.text(i);
			a.appendTo(li);
			var test = $('#pageP');
			test.after(li);
		}

		var pre = content.curPage - 1;
		if (pre <= 0)
			pre = 1;
		var herfPre =
				'search?' + '&page=' + pre + '&title=' + content.search_title
						+ '&lowPrice=' + content.lowPrice + '&highPrice='
						+ content.highPrice;

		$('#pageP').find('a').attr('href', herfPre);

		var next = content.curPage + 1;
		if (next > content.maxPage)
			next = content.maxPage;
		var herfNext =
				'search?' + '&page=' + next + '&title=' + content.search_title
						+ '&lowPrice=' + content.lowPrice + '&highPrice='
						+ content.highPrice;

		$('#pageN').find('a').attr('href', herfNext);
	}
</script>