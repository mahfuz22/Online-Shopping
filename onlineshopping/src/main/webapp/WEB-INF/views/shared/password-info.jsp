<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${not empty message}">
	<div class="container>">
		<div class="alert alert-success">
			${message}
		</div>
	</div>
</c:if>
<div class="panel panel-primary">
				<div class="panel-heading">
					<h3>Change Password</h3>
				</div>
				
				<div class="panel-body">
					<sf:form action="${contextRoot}/user/manage/passchange" class="form-horizontal" modelAttribute="userInfo" method="POST">
						<div class="form-group">
							<label class="control-lebel col-md-3" for="previuosPassword">Previous Password: </label>
							<div class="col-md-7">
								<sf:input type="text" path="previuosPassword" class="form-control"/>
								<sf:errors path="previuosPassword" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						<div class="form-group">
							<label class="control-lebel col-md-3" for=confirmPassword>New Password: </label>
							<div class="col-md-7">
								<sf:input type="password" path="confirmPassword" class="form-control"/>
								<sf:errors path="confirmPassword" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<%-- <sf:hidden path="id" value="${userInfo.id}"/> --%>
						
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