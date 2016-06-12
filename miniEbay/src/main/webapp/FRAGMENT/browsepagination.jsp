
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
		a.attr('href', 'browse?page=' + i + "&search_category_id="
				+ content.search_category_id);
		a.text(i);
		a.appendTo(li);
		var test = $('#pageP');
		test.after(li);
	}

	var pre = content.curPage - 1;
	if (pre <= 0)
		pre = 1;
	$('#pageP').find('a').attr(
			'href',
			'browse?page=' + pre + "&search_category_id="
					+ content.search_category_id);

	var next = content.curPage + 1;
	if (next > content.maxPage)
		next = content.maxPage;
	$('#pageN').find('a').attr(
			'href',
			'browse?page=' + next + "&search_category_id="
					+ content.search_category_id);
</script>