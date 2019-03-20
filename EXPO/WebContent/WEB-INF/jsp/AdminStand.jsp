<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AdminStand</title>
</head>
<body>
<form action="processInput" method="post">
<p align="right"><input type="submit" value="Logout"/></p>
  <fieldset>
    <legend>Registrering av stand:</legend>
    <p>Navn: <input type="text" name="navn" /></p>
    <p>ImageURL: <input type="url" name="image" /></p>
    <p><input type="submit" value="Oppdater" /></p>
  </fieldset>
</form>

</body>
</html>