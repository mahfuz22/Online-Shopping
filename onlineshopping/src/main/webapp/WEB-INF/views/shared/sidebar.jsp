

<div class="jumbotron">
	<div class="text-center">
		<p class="lead">Product Category</p>
	</div>
</div>


<div class="list-group">
	

	<c:forEach items="${categories}" var="category">
		<a href="${contextRoot}/show/category/${category.id}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
	</c:forEach>

	 
</div>