<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<title>${stand.name}</title>
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
<body>

	<div class="container">
		<div class="row m-2">
			<div class="col-4"></div>
			<div class="col-4">
				<img src="${stand.imageurl}" class="img-fluid " /> <br>
				<p>
					<font color="blue" size="+2">${msg}</font>
				</p>
			</div>
			<div class="col-4"></div>
		</div>
	</div>

	<form method="post" class="form mx-5"
		action="${pageContext.servletContext.contextPath}/StandAdmin">


		<div class="form-group row">
			<label for="name" class="col-sm-2 col-form-label">Navn: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="name" name="name"
					pattern="^[A-ZÆØÅ]{3}[0-9]{3}$" required placeholder="XXX000"
					value="${stand.name}">
			</div>
		</div>

		<div class="form-group row">
			<label for="epostAdmin" class="col-sm-2 col-form-label">Epost
				til admin: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="epostAdmin" name="email"
					placeholder="epost" value="${stand.epostadmin}">
			</div>
		</div>

		<div class="form-group row">
			<label for="pin" class="col-sm-2 col-form-label">Pin: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="pin" id="pin"
					placeholder="pin" required pattern="^[0-9]{4}$"
					value="${stand.pin}">
			</div>
		</div>
		<div class="form-group row">
			<label for="image" class="col-sm-2 col-form-label"> URL til
				bilde: </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="image" name="imageurl"
					placeholder="url" value="${stand.imageurl}">
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