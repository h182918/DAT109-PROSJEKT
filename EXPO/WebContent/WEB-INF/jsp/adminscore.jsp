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
<body class="bg-light">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand"
			href="${pageContext.servletContext.contextPath}/admin?jsp=1">EXPO</a>
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
			</ul>
		</div>
	</nav>


	<div class="container">
		<div class="row m-3">
			<div class="col">
				<div class="text-center my-4">
					<img
						src="https://exhibition.bergenexpo.no/theme/img/site-logo.png?v=1.0.0"
						class="img" />
				</div>
			</div>
		</div>
	</div>

	<div class="container">

		<table class="table table-striped" id="stands">
			<thead>
				<tr>
					<th scope="col" onclick="sortTableAlf(0)" style="cursor: pointer">Stand</th>
					<th scope="col" onclick="sortTableNum(1)" style="cursor: pointer">Score</th>
					<th scope="col" onclick="sortTableNum(2)" style="cursor: pointer">Antall
						Stemmer</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${overview}" var="stand">
					<tr>
						<td scope="row">${stand.stand.name}</td>
						<td>${stand.average }</td>
						<td>${stand.totalScore }</td>
						<td><a
							href="${pageContext.servletContext.contextPath}/admin?jsp=2&id=${stand.stand.id}"
							class="btn btn-dark">Se</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/js/sortTable.js"></script>

</body>
</html>