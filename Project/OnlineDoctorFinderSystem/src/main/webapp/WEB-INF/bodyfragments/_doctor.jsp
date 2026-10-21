<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class="container mt-2"
	style="position: relative; min-height: 100vh">
	<section class="contact">
		<div class="card shadow" style="width: 25rem;">
			<div class="card-body">
				<h5 class="card-title text-center">
					<i class="	fa fa-user-md-chat"></i><br>Doctor Info
				</h5>
				<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/doctor"
					modelAttribute="form">
					<br />
					<b><%@ include file="businessMessage.jsp"%></b>
					<sf:hidden path="id" />
					<div class="form-group">
						<s:bind path="drName">
							<label for="exampleInputEmail1">Dr. Name</label>
							<sf:input path="${status.expression}" placeholder="Enter Name"
								class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div>
					
					<div class="form-group">
						<s:bind path="specialityId">
							<label for="exampleInputEmail1">Speciality</label>
							<sf:select class="form-control" path="${status.expression}">
								<sf:option value="-1" label="---Select---" />
								<sf:options itemLabel="speciality" itemValue="id"
									items="${specialityList}" />
							</sf:select>
							<font color="red" style="font-size: 13px"><sf:errors path="${status.expression}" />
							</font>
						</s:bind>
					</div>
					<div class="form-group">
						<s:bind path="drExperience">
							<label for="exampleInputEmail1">Experience</label>
							<sf:input path="${status.expression}"
								placeholder="Enter Experience" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div>
					<div class="form-group">
						<s:bind path="location">
							<label for="exampleInputEmail1">Location</label>
							<sf:input path="${status.expression}"
								placeholder="Enter Location" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div>
					<div class="form-group">
						<s:bind path="appointmentFees">
							<label for="exampleInputEmail1">Fees</label>
							<sf:input path="${status.expression}"
								placeholder="Enter Appointment Fees" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div>


					<div class="container text-center">
						<input type="submit" class="btn btn-dark " value="Save"
							name="operation"> <input type="submit"
							class="btn btn-outline-dark " value="Reset" name="operation">
					</div>

				</sf:form>
			</div>
		</div>

	</section>
</div>