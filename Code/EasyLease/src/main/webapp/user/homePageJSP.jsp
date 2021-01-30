<%@ page import="com.easylease.EasyLease.model.car.Car" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/fragments/headerJSP.jsp"%>
<html>
<head>
  <title>Benvenuto su EasyLease!</title>
  <link href="${pageContext.request.contextPath}/user/homePageJSP.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/user/homePage.js">    </script>
</head>
<body>

<%
  List<Car> carList = (List<Car>) request.getSession().getAttribute("carList");
  if (carList == null) {
    response.sendRedirect((request).getContextPath()+"/HomePageServlet");
  }
%>

<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">

  <div class="carousel-inner">
    <div class="carousel-item active" data-bs-interval="10000">
      <a class="carLink" href="${pageContext.request.contextPath}/ViewCarServlet?model=kadjar">
        <img src="${pageContext.request.contextPath}/img/renault_kadjar.jpg" class="d-block w-100" alt="...">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h1 class="carTitle">Renault Kadjar</h1>
        <p>A partire da 319 &euro; al mese!</p>
      </div>
    </div>
    <div class="carousel-item" data-bs-interval="2000">
      <a class="carLink" href="${pageContext.request.contextPath}/ViewCarServlet?model=3008">
        <img src="${pageContext.request.contextPath}/img/peugeot_3008.jpg" class="d-block w-100" alt="...">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h1 class="carTitle">Peugeot 3008</h1>
        <p>A partire da 189 &euro; al mese!</p>
      </div>
    </div>
    <div class="carousel-item">
      <a class="carLink" href="${pageContext.request.contextPath}/ViewCarServlet?model=corsa">
        <img src="${pageContext.request.contextPath}/img/opel_corsa.jpg" class="d-block w-100" alt="...">
      </a>
      <div class="carousel-caption d-none d-md-block">
        <h1 class="carTitle">Opel Corsa</h1>
        <p>A partire da 189 &euro; al mese!</p>
      </div>
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleDark" role="button" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleDark" role="button" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </a>
</div>

<div class="Ricerca">
<form action='${pageContext.request.contextPath}/HomePageServlet' method='GET'>
  <select class="selectpicker" name="tipologia" id="TypeList" onchange="funct()">
    <option value="Tipologia" selected>Tipologia</option>
    <option>SUV</option>
    <option>Berlina</option>
    <option>Cabriolet</option>
    <option>Sport</option>
    <option>Station Wagon</option>
  </select>

  <select class="selectpicker" name="marca" id="BrandList" onchange="funct()">
    <option value="Marca" selected> Marca</option>
    <option value="Fiat">Fiat</option>
    <option value="Renault">Renault</option>
    <option value="Opel">Opel</option>
    <option value="Maserati">Maserati</option>
    <option value="Audi">Audi</option>
    <option value="Peugeot">Peugeot</option>
    <option value="Ford">Ford</option>
    <option value="Volkswagen">Volkswagen</option>
    <option value="Alfa Romeo">Alfa Romeo</option>
    <option value="Toyota">Toyota</option>
    <option value="Volvo">Volvo</option>
    <option value="Seat">Seat</option>
    <option value="BMW">BMW</option>
    <option value="Mercedes">Mercedes</option>
    <option value="Jaguar">Jaguar</option>
  </select>

  <select class="selectpicker" name="modello" id="ModelList" disabled>
    <option value="Modello" selected>Modello</option>
    <option value="Spider 124">Spider 124</option>
    <option value="Sporter">Sporter</option>
    <option value="Clio">Clio</option>
    <option value="Ghibli">Ghibli</option>
    <option value="Q3">Q3</option>
    <option value="5008">5008</option>
    <option value="Mokka X">Mokka X</option>
    <option value="2008">2008</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
    <option value="Clio">Clio</option>
  </select>

  <input type="submit" name="Cerca" value="Cerca" class="cerca">

</form>
</div>
<hr>
<div class= "flex">
  <% if(carList==null || (carList != null && carList.size()==0)){%> No product available. <% }
  else {
  for (Car car : carList) { %>
  <div class="obj">
    <a href="${pageContext.request.contextPath}/ViewCarServlet?model=<%=car.getModel() %>">
      <img class="CarImg" src="${pageContext.request.contextPath}/img/<%=car.getImage() %>"></a><br>
    <div class="carInfo">
    <%= car.getBrand() + " " + car.getModel()%> - da <%= car.getPrice()%>&euro;</div>
  </div>
  <%
      }
    } %>
</div>
<%@include file="/fragments/footerJSP.jsp"%>
</body>
</html>
