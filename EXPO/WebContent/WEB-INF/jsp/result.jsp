<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>${stand.name} Standen</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>

<div class="container">
    <div class="row m-3">
        <div class="col">
            <div class="text-center my-4">
                <img src="http://www.hib.no/siteassets/bilder-logoer/expo-logo-172.png" class="img"/>
            </div>
        </div>
    </div>
    <div class="row m-3">
        <div class="col">
            <div class="text-center">
                <div class="text-dark font-weight-bold">Du ga <span><font color="#b8860b"> ${vote} </font></span>
                    stjerner til ${stand.name}-standen
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row m-2">
        <div class="col-6">
            <div class="text-center">
                <img src="${stand.imageurl}" class="img-fluid "/>
            </div>
        </div>
        <div class="col-6">
            <div class="text-center align-content-center">
                <p style="font-size: larger; font-weight: bold">Total score: <span style="font-size: xx-large;color: gold">${avg}</span></p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
