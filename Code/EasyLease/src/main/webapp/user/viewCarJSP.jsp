<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  //Car car=(Car)request.getAttribute("car");
  Car car = new Car("CAAA111", "Peugeot", "3008", 249, "SUV", true, 5, "Automatico", 3.9f, 130, "Euro 6", 104, "Diesel", 1499, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.giordanoshop.com%2Fmacchina-elettrica-per-bambini-12v-jaguar-f-pace-blu-165431.html&psig=AOvVaw0LDHEwWDZw4MAQihxYutis&ust=1610037106337000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOjH5uDdh-4CFQAAAAAdAAAAABAD");
  request.getSession().setAttribute("role",null);
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
  <div style="float:right; margin:0.625em 6.25em 0.3125em 0.3125em; border-style: solid;font-size: large; font-size: 2em; background-color: lightgray;">
    <h3>Caratteristiche</h3><br><br>
    <div style="float:left; margin:0em 12.50em 0em 0.625em;">
      Porte:<br><br>
      Tipo: <br><br>
      Trasmissione:  <br><br>
      Consumo medio: <br><br>
      Cavalli:       <br><br>
      Classe di emissione:<br><br>
      Emissione CO2:  <br><br>
      Alimentazione:   <br><br>
      Cilindrata:     <br><br>

    </div>
    <div style="float:right; margin:0em 0.625em 0em 0em;">
      <%=car.getDoors()%> <br><br>
      <%=car.getType()%> <br><br>
      <%=car.getTransmision()%> <br><br>
      <%=car.getAvg_consumption()%> <br><br>
      <%=car.getHorse_power()%> <br><br>
      <%=car.getEmission_class()%> <br><br>
      <%=car.getCo2_emissions()%> <br><br>
      <%=car.getPowerSupply()%> <br><br>
      <%=car.getCapacity()%> <br><br>

    </div>
  </div>
  <div style="float:left; margin:0.625em 0.3125em 0.3125em 6.25em; font-size: large;">
    <p style="font-size: 5em"><%=car.getBrand()%> <%=car.getModel()%></p>
    <br>
    <img src="<%=car.getImage()%>" height=700 width=700 alt="" > <br><br>
    <div style="border-style: solid;width: 43.75em; height: 28.125em; font-size: large; align-content: center">
      <p style="font-size: 3em">A partire da: </p><br><br>

      <p style="font-size: 4em"> <%=car.getPrice()%> € </p>
      <p style="font-size: 2em">Al mese</p>
      <br>
      <form method="POST" action="../client/signInJSP.jsp">
        <input type="submit" class="btn btn-primary btn-lg" name="Registrati" value="Registrati">
      </form>
    </div>
  </div>
  <%}else{ if(request.getSession().getAttribute("role").equals("admin")){ %>
  <div style="float:right; margin:0.625em 6.25em 0.3125em 0.3125em; border-style: solid;font-size: large; font-size: 2em; background-color: lightgray;">
    <h3>Caratteristiche</h3><br><br>
    <div style="float:left; margin:0em 12.50em 0em 0.625em;">
      Porte:<br><br>
      Tipo: <br><br>
      Trasmissione:  <br><br>
      Consumo medio: <br><br>
      Cavalli:       <br><br>
      Classe di emissione:<br><br>
      Emissione CO2:  <br><br>
      Alimentazione:   <br><br>
      Cilindrata:     <br><br>

    </div>
    <div style="float:right; margin:0em 0.625em 0em 0em;">
      <%=car.getDoors()%> <br><br>
      <%=car.getType()%> <br><br>
      <%=car.getTransmision()%> <br><br>
      <%=car.getAvg_consumption()%> <br><br>
      <%=car.getHorse_power()%> <br><br>
      <%=car.getEmission_class()%> <br><br>
      <%=car.getCo2_emissions()%> <br><br>
      <%=car.getPowerSupply()%> <br><br>
      <%=car.getCapacity()%> <br><br>

    </div>
  </div>
  <div style="float:left; margin:0.625em 0.3125em 0.3125em 6.25em; font-size: large;">
    <p style="font-size: 5em"><%=car.getBrand()%> <%=car.getModel()%></p>
    <br>
    <img src="<%=car.getImage()%>" height=700 width=700 alt="" > <br><br>
    <div style="border-style: solid;width: 43.75em; height: 28.125em; font-size: large; align-content: center">
      <p style="font-size: 3em">A partire da: </p><br><br>

      <p style="font-size: 4em"> <%=car.getPrice()%> € </p>
      <p style="font-size: 2em">Al mese</p>
      <br>
      <form method="POST" action="../admin/updateCarJSP.jsp">
        <input type="submit" class="btn btn-primary btn-lg" name="Modifica Auto" value="Modifica">
      </form>
      <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.admin.DeleteCarServlet?id=<%=car.getId()%>">
        <input type="submit" class="btn btn-primary btn-lg" name="Elimina Auto" value="Elimina Auto">
      </form>
    </div>
  </div>

  <%}else{ if(request.getSession().getAttribute("role").equals("client")){ %>
    <div style="float:right; margin:0.625em 6.25em 0.3125em 0.3125em; border-style: solid;font-size: large; font-size: 2em; background-color: lightgray;">
      <h3>Caratteristiche</h3><br><br>
      <div style="float:left; margin:0em 12.50em 0em 0.625em;">
        Porte:<br><br>
        Tipo: <br><br>
        Trasmissione:  <br><br>
        Consumo medio: <br><br>
        Cavalli:       <br><br>
        Classe di emissione:<br><br>
        Emissione CO2:  <br><br>
        Alimentazione:   <br><br>
        Cilindrata:     <br><br>

      </div>
      <div style="float:right; margin:0em 0.625em 0em 0em;">
        <%=car.getDoors()%> <br><br>
        <%=car.getType()%> <br><br>
        <%=car.getTransmision()%> <br><br>
        <%=car.getAvg_consumption()%> <br><br>
        <%=car.getHorse_power()%> <br><br>
        <%=car.getEmission_class()%> <br><br>
        <%=car.getCo2_emissions()%> <br><br>
        <%=car.getPowerSupply()%> <br><br>
        <%=car.getCapacity()%> <br><br>

      </div>
    </div>
  <div style="float:left; margin:0.625em 0.3125em 0.3125em 6.25em; font-size: large;">
    <p style="font-size: 5em"><%=car.getBrand()%> <%=car.getModel()%></p>
    <br>
    <img src="<%=car.getImage()%>" height=700 width=700 alt="" > <br><br>
    <div style="border-style: solid;width: 43.75em; height: 28.125em; font-size: large; align-content: center">
      <p style="font-size: 3em">A partire da: </p><br><br>

      <p style="font-size: 4em"> <%=car.getPrice()%> € </p>
      <p style="font-size: 2em">Al mese</p>
    <br>
        <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&path=advisor/estimateManagementAdvisorJSP.jsp?id=<%=car.getId()%>">
          <input type="submit" class="btn btn-primary btn-lg" name="Richiedi preventivo" value="Richiedi preventivo">
        </form>
    </div>
  </div>

  <%}else{ if(request.getSession().getAttribute("role").equals("advisor")){ %>
  <div style="float:right; margin:0.625em 6.25em 0.3125em 0.3125em; border-style: solid;font-size: large; font-size: 2em; background-color: lightgray;">
    <h3>Caratteristiche</h3><br><br>
    <div style="float:left; margin:0em 12.50em 0em 0.625em;">
      Porte:<br><br>
      Tipo: <br><br>
      Trasmissione:  <br><br>
      Consumo medio: <br><br>
      Cavalli:       <br><br>
      Classe di emissione:<br><br>
      Emissione CO2:  <br><br>
      Alimentazione:   <br><br>
      Cilindrata:     <br><br>

    </div>
    <div style="float:right; margin:0em 0.625em 0em 0em;">
      <%=car.getDoors()%> <br><br>
      <%=car.getType()%> <br><br>
      <%=car.getTransmision()%> <br><br>
      <%=car.getAvg_consumption()%> <br><br>
      <%=car.getHorse_power()%> <br><br>
      <%=car.getEmission_class()%> <br><br>
      <%=car.getCo2_emissions()%> <br><br>
      <%=car.getPowerSupply()%> <br><br>
      <%=car.getCapacity()%> <br><br>

    </div>
  </div>
  <div style="float:left; margin:0.625em 0.3125em 0.3125em 6.25em; font-size: large;">
    <p style="font-size: 5em"><%=car.getBrand()%> <%=car.getModel()%></p>
    <br>
    <img src="<%=car.getImage()%>" height=700 width=700 alt="" > <br><br>
    <div style="border-style: solid;width: 43.75em; height: 28.125em; font-size: large; align-content: center">
      <p style="font-size: 3em">A partire da: </p><br><br>

      <p style="font-size: 4em"> <%=car.getPrice()%> € </p>
      <p style="font-size: 2em">Al mese</p>
      <br>
      <form method="POST" action="../index.jsp">
        <input type="submit" class="btn btn-primary btn-lg" name="Home" value="Home" >
      </form>
    </div>
  </div>
  <%}}}}%>
</div>
</body>
</html>