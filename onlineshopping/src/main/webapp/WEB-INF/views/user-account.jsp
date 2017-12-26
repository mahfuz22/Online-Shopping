
<div class="container">
	<div class="row">
		<!-- load the user side bar -->
		<div class="col-md-3">
			<%@include file="user-sidebar.jsp" %>
		</div>
		
		<!-- load the content -->
		<div class="col-md-9">
			<c:if test="${userClickManageAccount == true}">
				<%@include file="./shared/personal-info.jsp" %>
			</c:if>
			
			<c:if test="${userClickManagePassword == true}">
				<%@include file="./shared/password-info.jsp" %>
			</c:if>
			
			<c:if test="${userClickManageBillingAddress == true}">
				<%@include file="./shared/billing-info.jsp" %>
			</c:if>
			
			<!-- 
				<c:if test="${userClickManageShippingAddress == true}">
					<%@include file="./shared/shipping-info.jsp" %>
				</c:if>
			 -->
			
		</div>
	</div>
</div>