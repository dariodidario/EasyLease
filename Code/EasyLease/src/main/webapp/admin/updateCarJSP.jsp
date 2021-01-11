<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.easylease.EasyLease.model.car.Car" %>
<%String role=(String) request.getSession().getAttribute("role");
if(role==null){%>
  <html>
<head>
  <title>Update Car</title>
</head>
<body onload="window.location.href='fragments/error403.jsp'">

</body>
</html>
<%}else if(role.equalsIgnoreCase("admin")==false){ %>
<html>
<head>
  <title>Update Car</title>
</head>
<body onload="window.location.href='fragments/error403.jsp'">

</body>
</html>
<%}else {
%>
<%
  String id=""; String brand=""; String model=""; float price=0; String car_type=""; int doors=0;
  String trasmission=""; float avg_consumption=0; int horse_power=0; String emission_class="";
  int co2_emissions=0; String power_supply=""; int capacity=0; String image_path="";

  Car car=(Car) request.getSession().getAttribute("update_car");

  if(car!=null) {
    id=car.getId();
    brand = car.getBrand();
    model = car.getModel();
    price = car.getPrice();
    car_type = car.getType();
    doors = car.getDoors();
    trasmission = car.getTransmission();
    avg_consumption = car.getAvg_consumption();
    horse_power = car.getHorse_power();
    emission_class = car.getEmission_class();
    co2_emissions = car.getCo2_emissions();
    power_supply = car.getPowerSupply();
    capacity = car.getCapacity();
    image_path = car.getImage();
  }
%>
<html>
<head>
  <title>Update Car</title>
  <link rel="stylesheet" href="admin/updateCarCSS.css">
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="admin/updateCarJS.js"></script>

<%@include file="../fragments/headerJSP.jsp"%>
<div id="divAutoName">
  <label class="autoName" id="brandL"><%=brand%></label>
  <img class="matita" src="admin/image/matita.png" onclick="confirm('brand')">
  <label class="autoName" id="modelL"><%=model%></label>
  <img class="matita" src="admin/image/matita.png" onclick="confirm('model')">
</div>


<div id="divImage">
  <br>
  <img id="img_carL" src="img/<%=image_path%>" onerror="this.src='admin/image/nophoto.jpg'"/>
  <img class="matita_img" src="admin/image/matita.png" onclick="confirm('img_car')">
  <br>
</div>




<table class="characteristics">
  <tr><td colspan="3" align="center"><h2>Caratteristiche</h2></td></tr>
  <tr>
    <td>
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('car_type')">
      <label class="carDetail">Tipologia</label>
    </td>
    <td>
      <label class="carDetail" id="car_typeL"><%=car_type%></label>
    </td>
  </tr>
  <tr>
    <td>
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('doors')">
      <label class="carDetail">Porte</label>
    </td>
    <td>
      <label class="carDetail" id="doorsL"><%=doors%></label>
    </td>
  </tr>
  <tr>
    <td>
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('transmission')">
      <label class="carDetail">Cambio</label>
    </td>
    <td>
      <label class="carDetail" id="trasmissionL"><%=trasmission%></label>
    </td>
  </tr>
  <tr>
    <td width="50%">
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('avg_consumption')">
      <label class="carDetail">Consumo medio</label>
    </td>
    <td width="50%">
      <label class="carDetail" id="avg_consumptionL"><%=avg_consumption%></label>
      <label class="carDetail">&nbsp;L/100km</label>
    </td>
  </tr>
  <tr>
    <td>
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('horse_power')">
      <label class="carDetail">Cavalli</label>
    </td>
    <td>
      <label class="carDetail" id="horse_powerL"><%=horse_power%></label>
      <label class="carDetail">&nbsp;CV</label>
    </td>
  </tr>
  <tr>
    <td>
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('emission_class')">
      <label class="carDetail">CO&sup2;</label>
    </td>
    <td>
      <label class="carDetail" id="emission_classL"><%=emission_class%></label>
    </td>
  </tr>
  <tr>
    <td>
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('co2_emissions')">
      <label class="carDetail">Emissioni di CO&sup2;</label>
    </td>
    <td>
      <label class="carDetail" id="co2_emissionsL"><%=co2_emissions%></label>
      <label class="carDetail">&nbsp;g/km</label>
    </td>
  </tr>
  <tr>
    <td><img class="matita_table" src="admin/image/matita.png" onclick="confirm('power_supply')">
      <label class="carDetail">Alimentazione</label>
    </td>
    <td>
      <label class="carDetail" id="power_supplyL"><%=power_supply%></label>
    </td>
  </tr>
  <tr>
    <td><img class="matita_table" src="admin/image/matita.png" onclick="confirm('capacity')">
      <label class="carDetail">Cilindrata Motore</label>
    </td>
    <td>
      <label class="carDetail" id="capacityL"><%=capacity%></label>
      <label class="carDetail">&nbsp;cm&sup3;</label>
    </td>
  </tr>
  <tr>
    <td>
      <img class="matita_table" src="admin/image/matita.png" onclick="confirm('price')">
      <label class="carDetail">Price</label>
    </td>
    <td>
      <label class="carDetail" id="priceL"><%=price%></label>
      <label class="carDetail">&nbsp;&euro;/mese</label>
    </td>
  </tr>
</table>



<div id="divButton">
  <form action="UpdateCarServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="ID_Update" value="<%=id%>">
    <input type="hidden" id="brand" name="brand_Update" value="<%=brand%>">
    <input type="hidden" id="model" name="model_Update" value="<%=model%>">
    <input type="hidden" id="img_car" name="img_car_Update" value="<%=image_path%>">
    <input type="hidden" id="car_type" name="car_type_Update" value="<%=car_type%>">
    <input type="hidden" id="doors" name="doors_Update" value="<%=doors%>">
    <input type="hidden" id="transmission" name="transmission_Update" value="<%=trasmission%>">
    <input type="hidden" id="avg_consumption" name="avg_consumption_Update" value="<%=avg_consumption%>">
    <input type="hidden" id="horse_power" name="horse_power_Update" value="<%=horse_power%>">
    <input type="hidden" id="emission_class" name="emission_class_Update" value="<%=emission_class%>">
    <input type="hidden" id="co2_emissions" name="co2_emissions_Update" value="<%=co2_emissions%>">
    <input type="hidden" id="power_supply" name="power_supply_Update" value="<%=power_supply%>">
    <input type="hidden" id="capacity" name="capacity_Update" value="<%=capacity%>">
    <input type="hidden" id="price" name="price_Update" value="<%=price%>">
    <input type="submit" value="Conferma Modifiche" id="buttonUpdateCar">
  </form>
  <form action="DeleteCarServlet">
    <input type="hidden" name="ID_Delete" value="<%=id%>">
    <input type="submit" value="Elimina Auto" id="buttonDeleteCar">
  </form>
</div>
<hr>
<%@include file="../fragments/footerJSP.jsp"%>
<datalist id="autoBrand">
  <option value="Abarth">
  <option value="Alfa Romeo">
  <option value="Alpine">
  <option value="Aston Martin">
  <option value="Audi">
  <option value="Bentley">
  <option value="BMW">
  <option value="Cadillac">
  <option value="Chery">
  <option value="Chevrolet">
  <option value="Chrysler">
  <option value="Citroen">
  <option value="Cupra">
  <option value="Dacia">
  <option value="Daihatsu">
  <option value="Dallara">
  <option value="Dodge">
  <option value="DR">
  <option value="DS">
  <option value="Evo">
  <option value="Ferrari">
  <option value="Fiat">
  <option value="Fisker">
  <option value="Ford">
  <option value="Genesis">
  <option value="Giotti Victoria">
  <option value="Gonow">
  <option value="Great Wall">
  <option value="Haval">
  <option value="Honda">
  <option value="Hummer">
  <option value="Hyundai">
  <option value="Ineos">
  <option value="Infiniti">
  <option value="Isuzu">
  <option value="Jaguar">
  <option value="Jeep">
  <option value="Kia">
  <option value="Koenigsegg">
  <option value="Lada">
  <option value="Lamborgini">
  <option value="Lancia">
  <option value="Land Rover">
  <option value="Landwind">
  <option value="Lexus">
  <option value="Lotus">
  <option value="Mahindra">
  <option value="Martin Motors">
  <option value="Maserati">
  <option value="Maybach">
  <option value="Mazda">
  <option value="McLaren">
  <option value="Mercedes">
  <option value="MG">
  <option value="Mini">
  <option value="Mitsubishi">
  <option value="Morgan">Morgan
  <option value="Nissan">
  <option value="Opel">
  <option value="Pagani">
  <option value="Peugeot">
  <option value="Pininfarina">
  <option value="Polestar">
  <option value="Porsche">
  <option value="Qoros">
  <option value="Ram">
  <option value="Renault">
  <option value="Rolls Royce">
  <option value="Seat">
  <option value="Skoda">
  <option value="Smart">
  <option value="SsangYong">
  <option value="Subaru">
  <option value="Suzuki">
  <option value="Tata">
  <option value="Tesla">
  <option value="Toyota">
  <option value="Volkswagen">
  <option value="Volvo">
</datalist>
<datalist id="Emision_class">
  <option value="Euro1">
  <option value="Euro2">
  <option value="Euro3">
  <option value="Euro4">
  <option value="Euro5">
  <option value="Euro6">
</datalist>
<datalist id="autoCambio">
  <option value="Automatico">
  <option value="Manuale">
  <option value="Sequenziale">
</datalist>
<datalist id="autoPorte">
  <option value="3">
  <option value="4">
  <option value="5">
</datalist>
<datalist id="autoPower">
  <option value="Benzina">
  <option value="Diesel">
  <option value="GPL">
  <option value="Metano">
  <option value="Ibrido">
</datalist>
<datalist id="autoTipologia">
  <option value="Berlina">
  <option value="Bivolume">
  <option value="Cabriolet">
  <option value="Concept">
  <option value="Conpact">
  <option value="Coupè">
  <option value="Crossover">
  <option value="Golf cart">
  <option value="Limousine">
  <option value="Microcar">
  <option value="MiniVan">
  <option value="Monovolume">
  <option value="Offroad">
  <option value="Pick up">
  <option value="Roadster">
  <option value="Spider">
  <option value="Sport">
  <option value="Station wagon">
  <option value="Suv">
  <option value="Utility">
  <option value="Van">
  <option value="Veteran">
  <option value="Wagonette">
</datalist>
</body>
</html>
<%}%>
