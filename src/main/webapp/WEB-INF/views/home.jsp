<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

	<div class="container">
		<div class="card m-4">
			<div class="card-header">
				<h2 class="card-title">Search Flights</h2>
			</div>
			<div class="card-body">
				<form:form modelAttribute="inputFlight" method="post">
					<div class="row">
						<div class="col-md-4 col-sm-6 p-2">
							Arrival Location :
							<form:input cssClass="form-control" path="arrLocation" />
						</div>
						<div class="col-md-4 col-sm-6 p-2">
							Departure Location :
							<form:input cssClass="form-control" path="depLocation" />

						</div>

						<div class="col-md-4 col-sm-6 p-2">
							Flight Data :
							<form:input cssClass="form-control" path="flightDate" />
						</div>
						<div class="col-md-4 col-sm-6 p-2">
							Flight Class :
							<form:select cssClass="custom-select" path="flightClass">
								<form:option value="E" label="Economics" />
								<form:option value="B" label="Business" />
							</form:select>
						</div>
						<div class="col-md-4 col-sm-6 p-2">
							Sort By :
							<form:select cssClass="custom-select" path="outPutPref">
								<form:option value="F" label="Fare" />
								<form:option value="FD" label="Flight Duration" />
							</form:select>
						</div>
					</div>
					<br>
					<button type="submit" class="btn btn-primary p-2 btn-lg">Search</button>
				</form:form>
			</div>
		</div>
	</div>



	<c:if test="${searchedFlights != null}">

		<div class="container">

			<h2 class="text-center m-2">Searched Flights</h2>


			<table class="table table-bordered p-4 text-center">
				<thead>
					<tr>
						<th scope="col">Flight Number</th>
						<th scope="col">Departure Location</th>
						<th scope="col">Arrival Location</th>
						<th scope="col">Flight Time</th>
						<th scope="col">Flight Duration</th>
						<th scope="col">Fare</th>
						<th scope="col">Seat Availability</th>
						<th scope="col">Flight Class</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${searchedFlights}" var="flight">
						<tr>
							<th scope="row">${flight.flightNo }</th>
							<td>${flight.depLocation }</td>
							<td>${flight.arrLocation }</td>
							<td>${flight.flightTime }</td>
							<td>${flight.flightDuration }</td>
							<td>${flight.fare }</td>
							<td>${flight.seatAvail }</td>
							<td>${flight.flightClass }</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>

	</c:if>





</body>
</html>
