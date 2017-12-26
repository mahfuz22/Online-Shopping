<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<c:if test="${not empty message}">
	<div class="container>">
		<div class="alert alert-success">
			${message}
		</div>
	</div>
</c:if>

<div class="panel panel-primary">
				<div class="panel-heading">
					<h3>Address</h3>
				</div>
				
				<div class="panel-body">
					<sf:form action="${contextRoot}/user/manage/post/billing" class="form-horizontal" modelAttribute="billingAddress" method="POST">
						<div class="form-group">
							<label class="control-lebel col-md-3" for="addressLineOne">Address Line One: </label>
							<div class="col-md-7">
								<sf:input type="text" path="addressLineOne" class="form-control" value=""/>
								<sf:errors path="addressLineOne" element="em" cssClass="help-block"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="addressLineTwo">Address Line Two: </label>
							<div class="col-md-7">
								<sf:input type="text" path="addressLineTwo" class="form-control" value=""/>
								<sf:errors path="addressLineTwo" element="em" cssClass="help-block"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="city">City: </label>
							<div class="col-md-7">
								<sf:input type="text" path="city" class="form-control" value=""/>
								<sf:errors path="city" element="em" cssClass="help-block"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="postalCode">Postal Code: </label>
							<div class="col-md-7">
								<sf:input type="text" path="postalCode" class="form-control"/>
								<sf:errors path="postalCode" element="em" cssClass="help-block"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="state">State: </label>
							<div class="col-md-7">
								<sf:input type="text" path="state" class="form-control" value=""
								maxlength="15"/>
								<sf:errors path="state" element="em" cssClass="help-block"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="country">Country: </label>
							<div class="col-md-7">
								<sf:input type="text" path="country" class="form-control" value=""/>
								<sf:errors path="country" element="em" cssClass="help-block"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<button type="submit" class="btn btn-primary">
										Update
									</button>																	 
								</div>
						</div>
					</sf:form>
				</div>
			</div>