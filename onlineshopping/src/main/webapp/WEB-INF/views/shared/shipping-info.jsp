<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<div class="panel panel-primary">
	<div class="panel-heading">Shipping Information</div>
	<div class="panel-body">
		<div class="well">
			<%List<String> id=new ArrayList<String>();;
			id.add("shipping1");
			id.add("shipping2");
			id.add("shipping3");
			String active="active";
			int count=0;%>
			<ul class="nav nav-tabs pad">
				<c:forEach items="${shippingAddress}" var="sa" >
					<li class="<%=active%>"><a href="#<%=id.get(count++)%>" data-toggle="tab">Profile</a></li>
					<%active=""; %>
				</c:forEach>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane active in" id="<%=id.get(0)%>">
					<form id="tab" class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-md-3" for="addressLineOne">Address Line One:</label>
							<div class="col-md-7">
								<input type="text" name="addressLineOne" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3" for="addressLineOne">Address Line One:</label>
							<div class="col-md-7">
								<input type="text" name="addressLineOne" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3" for="addressLineOne">Address Line One:</label>
							<div class="col-md-7">
								<input type="text" name="addressLineOne" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3" for="addressLineOne">Address Line One:</label>
							<div class="col-md-7">
								<input type="text" name="addressLineOne" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3" for="addressLineOne">Address Line One:</label>
							<div class="col-md-7">
								<input type="text" name="addressLineOne" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3" for="addressLineOne">Address Line One:</label>
							<div class="col-md-7">
								<input type="text" name="addressLineOne" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3" for="addressLineOne">Address Line One:</label>
							<div class="col-md-7">
								<input type="text" name="addressLineOne" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<button type="submit" class="btn btn-primary">
										Update
									</button>																	 
								</div>
						</div>
					</form>
				</div>
				<div class="tab-pane fade" id="<%=id.get(1)%>">
					<form id="tab2">
						<label>New Password</label> <input type="password"
							class="input-xlarge">
						<div>
							<button class="btn btn-primary">Update</button>
						</div>
					</form>
				</div>
				
				<div class="tab-pane fade" id="<%=id.get(2)%>">
					<form id="tab2">
						<label>New Password</label> <input type="password"
							class="input-xlarge">
						<div>
							<button class="btn btn-primary">Update</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>