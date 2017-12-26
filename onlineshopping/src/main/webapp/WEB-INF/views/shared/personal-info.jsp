
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<c:if test="${not empty message}">
	<div class="alert alert-success">
		<div class="text-center">
			<h3>${message}</h3>
		</div>
	</div>
</c:if>


<div class="panel panel-primary">
				<div class="panel-heading">
					<h3>Personal Information</h3>
				</div>
				
				<div class="panel-body">
					<sf:form action="${contextRoot}/user/manage/update" class="form-horizontal" modelAttribute="userInfo" method="POST">
						<div class="form-group">
							<label class="control-lebel col-md-3" for="firstName">First Name: </label>
							<div class="col-md-7">
								<sf:input type="text" path="firstName" class="form-control"/>
								<sf:errors path="firstName" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="lastName">Last Name: </label>
							<div class="col-md-7">
								<sf:input type="text" path="lastName" class="form-control" />
								<sf:errors path="lastName" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="email">Email: </label>
							<div class="col-md-7">
								<sf:input type="text" path="email" class="form-control" />
								<sf:errors path="email" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-lebel col-md-3" for="contactNumber">Contact Number: </label>
							<div class="col-md-7">
								<sf:input type="text" path="contactNumber" class="form-control"
								maxlength="15"/>
								<sf:errors path="contactNumber" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						
						<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<input type="submit" name="submit" value="Update" class="btn btn-primary"/>
																											 
								</div>
						</div>
					</sf:form>
				</div>
			</div>