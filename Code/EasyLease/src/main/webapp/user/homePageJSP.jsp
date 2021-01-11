<%@ page import="com.easylease.EasyLease.model.car.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Benvenuto su EasyLease!</title>
  <link href="${pageContext.request.contextPath}/user/homePageJSP.css" rel="stylesheet">
</head>
<body>
<%@include file="/fragments/headerJSP.jsp"%>
<%@include file="ImageSlideshow.jsp"%>
<%
  List<Car> carList = (List<Car>) request.getAttribute("carList");
  if (carList == null) {
    response.sendRedirect(((HttpServletRequest)request).getContextPath()+"/HomePageServlet");
  }
%>
<div class="Ricerca">
<form action='${pageContext.request.contextPath}/HomePageServlet' method='GET'>

  <select class="selectpicker" name="tipologia">
    <option value="0" selected>Tipologia</option>
    <option>SUV</option>
    <option>Berlina</option>
    <option>Cabriolet</option>
    <option>Sport</option>
    <option>Station Wagon</option>
  </select>

  <select class="selectpicker" name="marca">
    <option value="0" selected> Marca</option>
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

  <select class="selectpicker" name="modello">
    <option value="0" selected>Modello</option>
    <option value="3008">3008</option>
    <option value="Spider 124">Spider 124</option>
    <option value="Clio">Clio</option>
  </select>

  <input type="submit" name="Cerca" value="Cerca" class="cerca">

</form>
</div>
<hr>
<div class= "flex">
  <% if(carList==null || (carList != null && carList.size()==0)){%> No product available. <% }
  else {
  Iterator<Car> it= carList.iterator();
  while(it.hasNext()){Car car = it.next();%>
  <div class="obj">
    <a href="${pageContext.request.contextPath}/ViewCarServlet?ID=<%=car.getId() %>"><img src="./foto/<%=car.getImage() %>"></a><br>
    <%= car.getBrand() + " " + car.getModel()%> -
      da <%= car.getPrice()%>&euro;
  </div>
  <%}} %>
</div>
<%@include file="/fragments/footerJSP.jsp"%>
</body>
</html>
