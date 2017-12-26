<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Billing Address</h4>
				</div>
				
				<div class="panel-body">
					<table class="table table-condensed table-bordered">
						<tr>
							<th rowspan="2">Billing Address</th>
							<th>Address Line One</th>
							<th>Address Line Two</th>
							<th>City</th>
							<th>State</th>
							<th>Country</th>
							<th>Postal Code</th>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${not empty billing}">
									<td>${billing.addressLineOne}</td>
									<td>${billing.addressLineTwo}</td>
									<td>${billing.city}</td>
									<td>${billing.state}</td>
									<td>${billing.country}</td>
									<td>${billing.postalCode}</td>
								</c:when>
								<c:otherwise>
									<td colspan="6" style="color:red; text-align:center"><strong>Biiling address is not available</strong></td>
								</c:otherwise>
							</c:choose>
							
						</tr>
						
						<tr>
							<th rowspan="2">Shipping Address</th>
							<th>Address Line One</th>
							<th>Address Line Two</th>
							<th>City</th>
							<th>State</th>
							<th>Country</th>
							<th>Postal Code</th>
						</tr>
						<c:choose>
							<c:when test="${not empty shipping}">
								<c:forEach items="${shipping}"  var="shipping">
									<td>${shipping.addressLineOne}</td>
									<td>${shipping.addressLineTwo}</td>
									<td>${shipping.city}</td>
									<td>${shipping.state}</td>
									<td>${shipping.country}</td>
									<td>${shipping.postalCode}</td>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td colspan="6" style="color:red; text-align:center"><strong>Shipping address is not available</strong></td>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</div>
		</div>
</div>