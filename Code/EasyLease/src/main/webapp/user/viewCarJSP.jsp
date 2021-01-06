<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  //Car car=(Car)request.getAttribute("car");
  Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV", true, 5, "Automatico", 3.9f, 130, "Euro 6", 104, "Diesel", 1499, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.giordanoshop.com%2Fmacchina-elettrica-per-bambini-12v-jaguar-f-pace-blu-165431.html&psig=AOvVaw0LDHEwWDZw4MAQihxYutis&ust=1610037106337000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOjH5uDdh-4CFQAAAAAdAAAAABAD");
  request.getSession().setAttribute("role","client");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="com.easylease.EasyLease.model.car.Car"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous">
<link rel="stylesheet" href="viewCar.css">
<head>
  <meta charset="ISO-8859-1">
  <title>View Car</title>

</head>
<body>
<div class="center">
  <%if(request.getSession().getAttribute("role")==null){%>
  <div id="caratteristiche" style="float:right; margin:10px 1000px 5px 5px; border-style: solid;font-size: large; background-color: #9B334E;">
    <h3>Caratteristiche</h3>
    Porte: <%=car.getDoors()%> <br>
    Tipo: <%=car.getType()%> <br>
    Trasmissione: <%=car.getTransmision()%> <br>
    Consumo medio: <%=car.getAvg_consumption()%> <br>
    Cavalli: <%=car.getHorse_power()%> <br>
    Classe di emissione: <%=car.getEmission_class()%> <br>
    Emissione CO2: <%=car.getCo2_emissions()%> <br>
    Alimentazione: <%=car.getPowerSupply()%> <br>
    Cilindrata: <%=car.getCapacity()%> <br>
  </div>
  <div style="float:left; margin:10px 5px 5px 100px; font-size: large;">
    <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
    <br>
    <img src="<%=car.getImage()%>" height=300 width=300 alt=""> <br><br>
    <div style="border-style: solid;width: 65px; height: 100%; font-size: large">
      <%=car.getPrice()%> €
    </div><br><br>
      <form method="POST" action="../client/signInJSP.jsp">
        <input type="submit" class="btn btn-primary btn-lg" name="Registrati" value="Registrati">
      </form>
  </div>
  <%}else{ if(request.getSession().getAttribute("role").equals("admin")){ %>
  <div id="caratteristiche" style="float:right; margin:10px 1000px 5px 5px; border-style: solid;font-size: large;">
    <h3>Caratteristiche</h3>
    Porte: <%=car.getDoors()%> <br>
    Tipo: <%=car.getType()%> <br>
    Trasmissione: <%=car.getTransmision()%> <br>
    Consumo medio: <%=car.getAvg_consumption()%> <br>
    Cavalli: <%=car.getHorse_power()%> <br>
    Classe di emissione: <%=car.getEmission_class()%> <br>
    Emissione CO2: <%=car.getCo2_emissions()%> <br>
    Alimentazione: <%=car.getPowerSupply()%> <br>
    Cilindrata: <%=car.getCapacity()%> <br>
  </div>
  <div style="float:left; margin:10px 5px 5px 100px; font-size: large;">
    <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
    <br>
    <img src="<%=car.getImage()%>" height=300 width=300 alt=""> <br><br>
    <div style="border-style: solid;width: 65px; height: 100%; font-size: large">
      <%=car.getPrice()%> €
    </div><br><br>
      <form method="POST" action="../admin/updateCarJSP.jsp">
        <input type="submit" class="btn btn-primary btn-lg" name="Modifica" value="Modifica">
      </form> <br>
      <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.admin.DeleteCarServlet?id=<%=car.getId()%>">
        <input type="submit" class="btn btn-primary btn-lg" name="Elimina Auto" value="Elimina Auto">
      </form>
  </div>
  <%}else{ if(request.getSession().getAttribute("role").equals("client")){ %>
    <div style="float:right; margin:10px 1000px 5px 5px; border-style: solid;font-size: large;">
      <h3>Caratteristiche</h3>
      Porte: <%=car.getDoors()%> <br>
      Tipo: <%=car.getType()%> <br>
      Trasmissione: <%=car.getTransmision()%> <br>
      Consumo medio: <%=car.getAvg_consumption()%> <br>
      Cavalli: <%=car.getHorse_power()%> <br>
      Classe di emissione: <%=car.getEmission_class()%> <br>
      Emissione CO2: <%=car.getCo2_emissions()%> <br>
      Alimentazione: <%=car.getPowerSupply()%> <br>
      Cilindrata: <%=car.getCapacity()%> <br>
    </div>
  <div style="float:left; margin:10px 5px 5px 100px; font-size: large;">
    <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
    <br>
    <img src="<%=car.getImage()%>" height=300 width=300 alt="" > <br><br>
    <div style="border-style: solid;width: 65px; height: 100%; font-size: large">
      <%=car.getPrice()%> €
    </div><br><br>
        <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&path=advisor/estimateManagementAdvisorJSP.jsp?id=<%=car.getId()%>">
          <input type="submit" class="btn btn-primary btn-lg" name="Richiedi preventivo" value="Richiedi preventivo">
        </form>
  </div>

  <%}else{ if(request.getSession().getAttribute("role").equals("advisor")){ %>
    <div id="caratteristiche" style="float:right; margin:10px 1000px 5px 5px; border-style: solid;font-size: large;">
      <h3>Caratteristiche</h3>
      Porte: <%=car.getDoors()%> <br>
      Tipo: <%=car.getType()%> <br>
      Trasmissione: <%=car.getTransmision()%> <br>
      Consumo medio: <%=car.getAvg_consumption()%> <br>
      Cavalli: <%=car.getHorse_power()%> <br>
      Classe di emissione: <%=car.getEmission_class()%> <br>
      Emissione CO2: <%=car.getCo2_emissions()%> <br>
      Alimentazione: <%=car.getPowerSupply()%> <br>
      Cilindrata: <%=car.getCapacity()%> <br>
    </div>
  <div style="float:left; margin:10px 5px 5px 100px; font-size: large;">
  <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
  <br>
  <img src="<%=car.getImage()%>" height=300 width=300 alt=""> <br><br>
  <div style="border-style: solid;width: 65px; height: 100%; font-size: large">
    <%=car.getPrice()%> €
  </div><br><br>
      <form method="POST" action="../index.jsp">
        <input type="submit" class="btn btn-primary btn-lg" name="Home" value="Home" >
      </form>
  </div>
  <%}}}}%>
</div>
</body>
</html>