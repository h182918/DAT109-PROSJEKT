<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<title>Admin ${stand.name}</title>
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
    <div class="row m-2">
        <div class="col-4">
        </div>
        <div class="col-4">
            <img src="${stand.imageurl}" class="img-fluid "/>
        </div>
        <div class="col-4">
        </div>
    </div>
</div>

	<form method="post" class="form mx-5"
		action="${pageContext.servletContext.contextPath}/admin">

		<div class="form-group row">
			<label for="staticId" class="col-sm-2 col-form-label">Stand
				Id: </label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="staticId" value="${stand.id}">
			</div>
		</div>

		<div class="form-group row">
			<label for="name" class="col-sm-2 col-form-label">Navn: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="name"
					placeholder="XXX000" value="${stand.name}">
			</div>
		</div>

		<div class="form-group row">
			<label for="epostAdmin" class="col-sm-2 col-form-label">Epost
				til admin: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="epostAdmin"
					placeholder="epost" value="${stand.epostadmin}">
			</div>
		</div>

		<div class="form-group row">
			<label for="pin" class="col-sm-2 col-form-label">Pin: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="pin" placeholder="epost"
					value="${stand.pin}">
			</div>
		</div>
		<div class="form-group row">
			<label for="image" class="col-sm-2 col-form-label"> URL til
				bilde: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="image" placeholder="url"
					value="${stand.imageurl}">
			</div>
		</div>
		<div class="form-group row">
			<div class="col">
				<button type="submit" class="btn btn-primary float-right px-5 py-2">Lagre</button>
			</div>
		</div>
		
	</form>

</body>
</html>