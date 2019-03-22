<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrer</title>
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

		<div class="row align-items-center">
			<div class="col "></div>
			<div class="col-md-6 col-sm-4 align-self-center">
				<p>
					<font color="red">${error}</font>
				</p>
				<form method="post" action="registrer">
					<div class="form-group">
						<label for="email">Pin:</label> <input type="password"
							class="form-control" name="pinIn" aria-describedby="passwordHelp"
							placeholder="Pin" required>
					</div>
					<input type="hidden" name="email" value="${email}">
					<input type="hidden" name="pin" value="${pin}">
					<button type="submit" class="btn btn-primary">Registrer</button>
				</form>

			</div>
			<div class="col "></div>
		</div>
	</div>


</body>
</html>