<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/home/login/doctor" />

<c:url var="addSearch" value="/home/login/user/doctor/search" />

<c:url var="editUrl" value="/home/login/user/doctor?id=" />

<br>
<div class="container mt-2 mb-3"
	style="position: relative; min-height: 100vh">
	<section class="contact">

		<div class="card shadow">
			<div class="card-body">
				<h5 class="card-title text-center">
					<i class="fas fa-user-md"></i><br>Find Doctor
				</h5>
				<sf:form method="post"
					action="${pageContext.request.contextPath}/home/login/user/doctor/search"
					modelAttribute="form">
					<br />
					<b class="text-center"><%@ include file="businessMessage.jsp"%></b>
					<div class="row">
						<div class="col-lg-6">
						<div class="form-group">
						<s:bind path="drName">
							<label for="exampleInputEmail1">Doctor Name</label>
							<sf:input path="${status.expression}"
								placeholder="Search by Doctor Name" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div>
						</div>
						<div class="col-lg-6">
						<div class="form-group">
						<s:bind path="drExperience">
							<label for="exampleInputEmail1">Doctor Experience</label>
							<sf:input path="${status.expression}"
								placeholder="Search by Experience" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div> 
						</div>
						</div>
						<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
						<s:bind path="location">
							<label for="exampleInputEmail1">Location</label>
							<sf:input path="${status.expression}"
								placeholder="Search by Location" class="form-control" />
							<font color="red" style="font-size: 13px"><sf:errors
									path="${status.expression}" /></font>
						</s:bind>
					</div> 
						</div>
						<div class="col-lg-6">
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
						</div>
					</div>
				
					
					<div class="col text-center">
						<input type="submit" class="btn btn-sm btn-dark" name="operation"
							value="Search"></input> or <input type="submit"
							class="btn btn-sm btn-outline-dark" name="operation"
							value="Reset">
					</div>
					<br/>
					<sf:input type="hidden" path="pageNo" />
					<sf:input type="hidden" path="pageSize" />

					<sf:input type="hidden" path="listsize" />
					<sf:input type="hidden" path="total" />
					<sf:input type="hidden" path="pagenosize" />
				<div class="table-responsive">	
					<table class="table table-bordered">
						<thead>
							<tr>
								<c:if test="${sessionScope.user.roleId == 1}">
									<th scope="col">Select
										All</th>
								</c:if>
								<th scope="col">#</th>
								<th scope="col">Dr. Name</th>
								<th scope="col">Experience</th>
								<th scope="col">Speciality</th>
								<th scope="col">Location</th>
								<th scope="col">Fees</th>

								<c:if test="${sessionScope.user.roleId == 2}">
									<th scope="col">Select</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="d" varStatus="doc">
								<tr>
									
									<td scope="row">${doc.index+1}</td>
									<td scope="row">${d.drName}</td>
									<td scope="row">${d.drExperience} years</td>
									<td scope="row">${d.speciality.speciality}</td>
									
									<td scope="row">${d.location}</td>
									<td scope="row">${ d.appointmentFees}</td>
									<c:if test="${sessionScope.user.roleId == 2}">
									
										<td><input type="checkbox" class="case" name="ids"
											value="${d.id}"></td>
									</c:if>
									<c:if test="${sessionScope.user.roleId == 1}">
								<td><a class="text-dark" href="${editUrl}${d.id}"
									><i class="fas fa-edit"></i></a></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>	
					<div class="clearfix text-center">
						<c:if test="${sessionScope.user.roleId == 2 && listsize!= 0}">
							<input type="submit" name="operation"
								class="btn btn-sm btn-danger float-start"
								value="Proceed for Appointment">
						</c:if>


						<nav aria-label="Page navigation example float-end">
							<ul class="pagination justify-content-end"
								style="font-size: 13px">
								<li class="page-item"><input type="submit" name="operation"
									class="page-link"
									<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
									value="Previous"></li>
								<c:forEach var="i" begin="1" end="${(listsize/10)+1}">
									<c:if test="${i== pageNo}">
										<li class="page-item active"><a
											class="page-link activate" href="${addSearch}?pageNo=${i}">${i}</a></li>
									</c:if>
									<c:if test="${i != pageNo}">
										<li class="page-item"><a class="page-link"
											href="${addSearch}?pageNo=${i}">${i}</a></li>
									</c:if>
								</c:forEach>
								<li class="page-item"><input type="submit" name="operation"
									class="page-link"
									<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
									value="Next"></li>
							</ul>
						</nav>
					</div>


				</sf:form>
			</div>
		</div>
	</section>
</div>