<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09/01/2021
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/ImageSlideshow.css">
</head>
<body>
<div class="css-slider">
    <input id="slide-1" type="radio" name="slides" checked>
    <section class="slide slide-one">
        <a href="${pageContext.request.contextPath}/ViewCarServlet?model=<%="v60"%>"><h1 class="carContainer">Volvo v60 - da 379 euro al mese!
            <img src="${pageContext.request.contextPath}/img/volvo_v60.jpg" class="carImage"></h1></a>
        <nav class="navImg">
            <label for="slide-3" class="prev">&#10094;</label>
            <label for="slide-2" class="next">&#10095;</label>
        </nav>
    </section>
    <input id="slide-2" type="radio" name="slides">
    <section class="slide slide-two">
        <a href="${pageContext.request.contextPath}/ViewCarServlet?model=<%="corsa"%>"><h1 class="carContainer">Opel Corsa - da 199 euro al mese!
            <img src="${pageContext.request.contextPath}/img/opel_corsa.jpg" class="carImage"></h1></a>
        <nav class="navImg">
            <label for="slide-1" class="prev">&#10094;</label>
            <label for="slide-3" class="next">&#10095;</label>
        </nav>
    </section>
    <input id="slide-3" type="radio" name="slides">
    <section class="slide slide-three">
        <a href="${pageContext.request.contextPath}/ViewCarServlet?model=<%="3008"%>"><h1 class="carContainer">Peugeot 3008 - da 249 euro al mese!
            <img src="${pageContext.request.contextPath}/img/peugeot_3008.jpg" class="carImage"></h1></a>
        <nav class="navImg">
            <label for="slide-2" class="prev">&#10094;</label>
            <label for="slide-1" class="next">&#10095;</label>
        </nav>
    </section>

</div>

</body>
</html>
