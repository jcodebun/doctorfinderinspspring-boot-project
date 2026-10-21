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
		<div class="card shadow " style="width: 50rem;">
			<div class="card-body" align="justify">
				<h5 class="card-title text-center">
					<i class="	fa fa-user-md-chat"></i><br>WELCOME!
					PLEASE VIEW DOCTOR INFORMATION
				</h5>
				<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/user/appointment"
					modelAttribute="form">
					<br />
					<b><%@ include file="businessMessage.jsp"%></b>
					<sf:hidden path="id" />
					<div class="row">
						<div class="col-lg-6">
						<img src="${pageContext.request.contextPath}/resources/img/doc2.png" class="ml-5 w-50" alt="Not Found">
						</div>
						<div class="col-lg-6">
						<h5 class="card-title text-center"><strong>The name of doctor is DR. ${form.getDrName()}</strong><br/></h5>
			<h5 class="card-title text-center"><strong>Specialization : ${form.getSpeciality().getSpeciality()}</strong><br/></h5>
			<h5 class="card-title text-center"><strong>Location : ${form.getLocation()}</strong><br/></h5>
			<h5 class="card-title text-center"><strong>Experience : ${form.getDrExperience()}</strong><br/></h5>
			<h5 class="card-title text-center"><strong>Fees : ${form.getAppointmentFees()}</strong><br/></h5>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-6">
							Preferred Appointment Date: <input type="date" class="form-control" name="date" required="required" />
						</div>
						<div class="col-lg-6">
							Preferred time for Appointment: 
		<select name="timeslot" class="form-control" >
			<option value="9 am - 10 am"> 9 am - 10 am </option>
			<option value="10 am - 11 am"> 10 am - 11 am </option>
			<option value="11 am - 12 pm"> 11 am - 12 pm </option>
			<option value="12 pm - 1 pm"> 12 pm - 1 pm </option>
			<option value="1 pm - 2 pm"> 1 pm - 2 pm </option>
			<option value="2 pm - 3 pm"> 2 pm - 3 pm </option>
			<option value="3 pm - 4 pm"> 3 pm - 4 pm </option>
			<option value="4 pm - 5 pm"> 4 pm - 5 pm </option>
			<option value="5 pm - 6 pm"> 5 pm - 6 pm </option>
			<option value="6 pm - 7 pm"> 6 pm - 7 pm </option>
		</select>
						</div>
						
					</div>
			<div class="container text-center mt-2">
						<input type="submit" class="btn btn-dark " value="Take Appointment"
							name="operation"> 
					</div>
					
				</sf:form>
			</div>
		</div>

	</section>
</div>