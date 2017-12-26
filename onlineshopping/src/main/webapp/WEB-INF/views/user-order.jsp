<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Order Details</h3>
		</div>
		
		<div class="panel-body">
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th>Order Id</th>
						<th>User Id</th>
						<th>Product Name</th>
						<th>No. of Product</th>
						<th>Buying Price</th>
						<th>Total Price</th>
						<th>Order Date</th>
					</tr>
				</thead>
				<tbody>
					
						<c:choose>
							<c:when test="${not empty orderList}">
								<c:forEach items="${orderList}" var="orderList">
									<tr>
										<td>${orderList.id}</td>
										<td>${orderList.user.id}</td>
										<c:forEach items="${orderList.orderItems}" var="orderItem">
											<td>${orderItem.product.name}</td>
											<td>${orderItem.productCount}</td>
											<td>&#2547; ${orderItem.buyingPrice} /-</td>
											<td>&#2547; ${orderItem.total} /-</td>
										</c:forEach>
										<td>${orderList.orderDate}</td>
									</tr>
								</c:forEach>
							</c:when>
							
							<c:otherwise>
								<tr>
									<td colspan="6" style="color:red; text-align:center"><strong>Order is not available for this user</strong></td>
								</tr>
							</c:otherwise>
						</c:choose>
					
				</tbody>
			</table>	
		</div>
	</div>
</div>