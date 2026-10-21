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
					<i class="	fa fa-user-md-chat"></i><br>Confirm Appointment Page
				</h5>
				<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/user/appointment"
					modelAttribute="aform">
					<br />
					<b class="text-center"><%@ include file="businessMessage.jsp"%></b>
				
					
					<div class="row">
							<div class="col-lg-6">
								<h6>Please Note your Appointment Number :</h6>
							</div>
							<div class="col-lg-6">
								<h6>ODF_APPNO_${appBean.getId() }</h6>
							</div>
					</div>
					<div class="row">
							<div class="col-lg-6">
								<h6>Please Note your Dr. Name :</h6>
							</div>
							<div class="col-lg-6">
								<h6>Dr. ${appBean.getDoctor().getDrName() }</h6>
							</div>
					</div>
					<div class="row">
							<div class="col-lg-6">
								<h6>Please Note your Appointment Date :</h6>
							</div>
							<div class="col-lg-6">
								<h6>${appBean.getDateOfAppointment() }</h6>
							</div>
					</div>
					<div class="row">
							<div class="col-lg-6">
								<h6>Please Note your Appointment Slot :</h6>
							</div>
							<div class="col-lg-6">
								<h6>${appBean.getSlot() }</h6>
							</div>
					</div>
					<div class="container text-center">
						<p style="color: red;">Wait for the Admin to approve the appointment</p>
						<span class="btn btn btn-sm btn-info">Pending...</span>
						
					</div>
					
				</sf:form>
			</div>
		</div>

	</section>
</div>