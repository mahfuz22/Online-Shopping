<c:if test="${not empty message}">
	<div class="container>">
		<div class="alert alert-success">
			${message}
		</div>
	</div>
</c:if>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3>
						Admin Information
						</h3>
				</div>
				<div class="panel-body">
					<div class="well">
						<ul class="nav nav-tabs pad">
							<li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
							<li><a href="#profile" data-toggle="tab">Password</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane active in" id="home">
								<sf:form action="${contextRoot}/manage/post/adminInfo" method="POST" modelAttribute="userInfo" id="tab" class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-md-3" for="firstName">First Name:</label>
										<div class="col-md-6">
											<sf:input type="text" path="firstName" class="form-control" />
											<sf:errors path="firstName" element="em" cssClass="help-block"></sf:errors>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3" for="lastName">Last Name:</label>
										<div class="col-md-6">
											<sf:input type="text" path="lastName" class="form-control" />
											<sf:errors path="lastName" element="em" cssClass="help-block"></sf:errors>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3" for="email">Email:</label>
										<div class="col-md-6">
											<sf:input type="text" path="email" class="form-control" />
											<sf:errors path="email" element="em" cssClass="help-block"></sf:errors>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3" for="contactNumber">Contact Number:</label>
										<div class="col-md-6">
											<sf:input type="text" path="contactNumber" class="form-control" />
											<sf:errors path="contactNumber" element="em" cssClass="help-block"></sf:errors>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<button type="submit" class="btn btn-primary">
												Update</button>
										</div>
									</div>
								</sf:form>
							</div>
							<div class="tab-pane fade" id="profile">
								<sf:form action="${contextRoot}/manage/post/adminPass" method="POST" modelAttribute="userInfo" id="tab2" class="form-horizontal">
									<div class="form-group">
										<label class="col-md-3 control-label" for="previuosPassword">Previous Password:</label>
										<div class="col-md-6">
											<sf:input type="password" path="previuosPassword" class="form-control"/>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label" for="confirmPassword">Previous Password:</label>
										<div class="col-md-6">
											<sf:input type="password" path="confirmPassword" class="form-control"/>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<button type="submit" class="btn btn-primary">
												Update</button>
										</div>
									</div>
								</sf:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>