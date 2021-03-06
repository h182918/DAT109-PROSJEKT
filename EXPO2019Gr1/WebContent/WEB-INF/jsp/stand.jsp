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
<body style="background-color:#004357">

<div class="container">
    <div class="row m-3">
			<div class="col">
				<div class="text-center my-3">
					<img src="https://hvl.no/Static/internett/images/logo-no.png"
						class="img img-fluid" /> 
					<img
						src="https://i.imgur.com/8YhCXdF.png"
						class="img img-fluid" />
				</div>
			</div>
		</div>
    <div class="row m-3">
        <div class="col">
            <div class="text-center">
                <div class="font-weight-bold" style="color:white">Gi din stemme til ${stand.name}-standen:</div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row m-2">
        <div class="col-3">
        </div>
        <div class="col-6">
            <img src="${stand.imageurl}" class="img-fluid "/>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>
<div class="container">
    <div class="row my-2">
        <c:forEach var="i" begin="1" end="5">
            <div class="col nopadding">
                <p class="text-center" id="star${i}" style="color:lightgrey;font-size:10vw;cursor:pointer" onclick="vote(${i})">&#9733;</p>
            </div>
        </c:forEach>
    </div>
    <div class="row m-3">
        <div class="col">
            <div class="text-center my-4">
                <form name="sendVote" id="sendVote" method="post" action="Stand">
                    <input type="hidden" value="0" name="vote" id="vote"/>
                    <input type="hidden" value="${stand.id}" name="standId" id="StandId"/>
                    <button type="submit" form="sendVote" class="btn px-5" style="color:white;background-color: #00AFBA">Stem!</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/js.js"></script>
<c:if test="${vote!=null}">
    <script>vote(${vote.score})</script>
</c:if>
</body>
</html>
