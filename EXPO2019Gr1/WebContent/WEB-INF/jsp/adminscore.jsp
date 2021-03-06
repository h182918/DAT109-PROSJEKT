<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<title>EXPO Oversikt</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</head>
<body style="background-color: #004357">

	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background-color: #004357">
		<a class="navbar-brand"
			href="${pageContext.servletContext.contextPath}/admin?jsp=1"><img
			src="https://i.imgur.com/8YhCXdF.png" class="img" width="150vw" /></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.servletContext.contextPath}/admin?jsp=1">Oversikt<span
						class="sr-only">(current)</span></a></li>
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.servletContext.contextPath}/logout">Logg ut</a></li>
			</ul>
		</div>
	</nav>


	<div class="container">
		<div class="row m-3">
			<div class="col">
				<div class="text-center my-3">
					<img src="https://hvl.no/Static/internett/images/logo-no.png"
						class="img img-fluid" />
				</div>
			</div>
		</div>
	</div>

	<div class="container">

		<table class="table table-striped" id="stands"
			style="background-color: #004357; color: white">
			<thead>
				<tr style="background-color: #00AFBA; border: 2px solid #00AFBA">
					<th scope="col" onclick="sortTableAlf(0)" style="cursor: pointer">Stand</th>
					<th scope="col" onclick="sortTableNum(1)" style="cursor: pointer">Score</th>
					<th scope="col" onclick="sortTableNum(2)" style="cursor: pointer">Antall
						Stemmer</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${overview}" var="stand">
					<tr
						style="border-top: 2px solid #00AFBA; border-bottom: 2px solid #00AFBA">
						<td scope="row">${stand.stand.name}</td>
						<td>${stand.average }</td>
						<td>${stand.totalScore }</td>
						<td><a
							href="${pageContext.servletContext.contextPath}/admin?jsp=2&id=${stand.stand.id}"
							class="btn px-2" style="background-color: #FFCF01; color: white">Se</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/js/sortTable.js"></script>

</body>
</html>