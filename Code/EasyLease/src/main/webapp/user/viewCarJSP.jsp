<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  //Car car=(Car)request.getAttribute("car");
  Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV", true, 5, "Automatico", 3.9f, 130, "Euro 6", 104, "Diesel", 1499, "D:\\Desktop\\Cars\\audi_a3.jpg");
  request.getSession().setAttribute("role","admin");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="com.easylease.EasyLease.model.car.Car"%>
<head>
  <meta charset="ISO-8859-1">
  <title>View Car</title>
</head>
<body>
  <%if(request.getSession().getAttribute("role")==null){%>
  <div id="caratteristiche" style="float:right; margin:10px 100px 5px 5px; border-style: solid;font-size: large;">
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
  <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
  <br><br><br>
  <img src="<%=car.getImage()%>" alt=""> <br><br>
  <div style="border-style: solid;width: 60px; height: 100%; background-color: red; font-size: large">
    <%=car.getPrice()%>
  </div><br><br>
  <div id="azioni" style="float:left; margin:10px 5px 5px 100px">
    <a href="#"><input type="button" name="Registrati" value="Registrati" ></a>
  </div>

  <%}else{ if(request.getSession().getAttribute("role").equals("admin")){ %>
  <div id="caratteristiche" style="float:right; margin:10px 100px 5px 5px; border-style: solid;font-size: large;">
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
  <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
  <br><br><br>
  <img src="<%=car.getImage()%>" alt=""> <br><br>
  <div style="border-style: solid;width: 60px; height: 100%; background-color: red; font-size: large">
    <%=car.getPrice()%>
  </div><br><br>
  <div id="azioni" style="float:left; margin:10px 5px 5px 100px">
    <form method="POST" action="../admin/updateCarJSP.jsp">
      <input type="submit" name="Modifica" value="Modifica">
    </form> <br><br>
    <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.admin.DeleteCarServlet?id=<%=car.getId()%>">
      <input type="submit" name="Elimina Auto" value="Elimina Auto">
    </form>
  </div>

  <%}else{ if(request.getSession().getAttribute("role").equals("user")){ %>
    <div id="caratteristiche" style="float:right; margin:10px 100px 5px 5px; border-style: solid;font-size: large;">
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
  <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
  <br><br><br>
  <img src="<%=car.getImage()%>" alt=""> <br><br>
  <div style="border-style: solid;width: 60px; height: 100%; background-color: red; font-size: large">
    <%=car.getPrice()%>
  </div><br><br>
    <div id="azioni" style="float:left; margin:10px 5px 5px 100px">
      <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&path=advisor/estimateManagementAdvisorJSP.jsp?id=<%=car.getId()%>">
        <input type="submit" name="Richiedi preventivo" value="Richiedi preventivo">
      </form>
    </div>


  <%}else{ if(request.getSession().getAttribute("role").equals("advisor")){ %>
    <div id="caratteristiche" style="float:right; margin:10px 100px 5px 5px; border-style: solid;font-size: large;">
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
  <h1><%=car.getBrand()%> <%=car.getModel()%></h1>
  <br><br><br>
  <img src="<%=car.getImage()%>" alt=""> <br><br>
  <div style="border-style: solid;width: 60px; height: 100%; background-color: red; font-size: large">
    <%=car.getPrice()%>
  </div><br><br>
    <div id="azioni" style="float:left; margin:10px 5px 5px 100px">
      <form method="POST" action="../index.jsp">
        <input type="submit" name="Home" value="Home" >
      </form>
    </div>
  <%}}}}%>

</body>
</html>