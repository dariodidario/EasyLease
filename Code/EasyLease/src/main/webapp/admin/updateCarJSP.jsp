<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.easylease.EasyLease.model.car.Car" %>
<%@page import="com.easylease.EasyLease.model.car.DBCarDAO" %>
<%@ page import="com.easylease.EasyLease.model.car.CarDAO" %>
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
<% String id=(String) request.getSession().getAttribute("Car_id");
  Car car=null;
  if(id!=null&&id.equalsIgnoreCase("")==false) {
    CarDAO carDAO = DBCarDAO.getInstance();
    car = carDAO.retrieveById(id);
  }
  String brand=""; String model=""; float price=0; String car_type=""; boolean visibility=false; int doors=0;
  String trasmission=""; float avg_consumption=0; int horse_power=0; String emission_class="";
  int co2_emissions=0; String power_supply=""; int capacity=0; String image_path="";

  if(car!=null) {
    brand = car.getBrand();
    model = car.getModel();
    price = car.getPrice();
    car_type = car.getType();
    visibility = car.getVisibility();
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
</head>
<style type="text/css">
  hr{
    border: 1px solid #dec717;
    width: 50%;
    alignment: left;
    margin-left: 10%;
    position: absolute;
    bottom: 20%;
  }
  label.carDetail{
    font-size: 1.5vw;
  }
  .autoName{
    width: 15%;
    font-size: 2vw;
    background: none;
    border: none;
    border-bottom: solid #dec717;
  }

  table.characteristics{
    position: absolute;
    top: 20.5%;
    right: 1%;
    background: #9b334e;
    width: 35%;
    height: 59%;
    margin-bottom: 2px;
    border-right: medium solid #dec717;
    border-top: medium solid #dec717;
    border-bottom: medium solid #dec717;
  }
  #buttonUpdateCar:hover{
    background: #800000;
  }
  #buttonUpdateCar{
    background: #9b334e;
    position: absolute;
    left: 30%;
    top: 66%;
    width: 15%;
    height: 5%;
    font-size: 1.5vw;
    border-bottom:medium solid #dec717;
    border-radius: 5px;
  }
  #buttonDeleteCar:hover{
    background: #800000;
  }
  #buttonDeleteCar{
    background: #9b334e;
    position: absolute;
    top: 72%;
    left: 32%;
    width: 12%;
    height: 5%;
    font-size: 1.5vw;
    border-bottom:medium solid #dec717;
    border-radius: 5px;
  }
  #divAutoName{
    position: absolute;
    top: 20.5%;
    left: 5%;
    width: 59%;
  }
  #divImage{
    top: 23%;
    position: absolute;
    left: 5%;
    width: 59%;
  }
  #img_carL{
    max-width: 40%;
    max-height: 30%;
    position: relative;
    left: 10%;
  }
  .matita{
    max-width: 2%;
    max-height: 2%;
  }
  .matita_table{
    max-width: 5%;
    max-height: 5%;
  }
  .matita_img{
    position: relative;
    left: 10%;
    max-width: 2%;
    max-height: 2%;
  }
  .matita:hover{
    max-width: 2%;
    max-height: 2%;
    border-bottom: 2px solid #dec717;
  }
  .matita_table:hover{
    max-width: 5%;
    max-height: 5%;
    border-bottom: 2px solid #dec717;
  }
  .matita_img:hover{
    position: relative;
    left: 10%;

    max-width: 2%;
    max-height: 2%;
    border-bottom: 2px solid #dec717;
  }

  input[type=file]::-webkit-file-upload-button {
    border-bottom: medium solid #dec717;
    border-radius: 5px;
    background-color: #9b334e;
  }
  input[type=file]::-webkit-file-upload-button:hover {
    background-color: #800000;
  }

</style>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>
  function readURL(input) {
    if (input.files && input.files[0]) {
      for(var i=0;i<input.files.length;i++) {
        var reader = new FileReader();
        var img='#img_carL';
        reader.onload = function (e) {
          $(img)
                  .attr('src', e.target.result)
        };

        reader.readAsDataURL(input.files[i]);

      }
    }
  }

  var vis = 1000;
  window.confirm = function(message) {
    var w = 400;
    var h = 350;
    var l = Math.floor((screen.width-w)/2);
    var t = Math.floor((screen.height-h)/2);
    var a = document.createElement('div');
    var input = document.createElement('input');
    var y = document.createElement('button');
    var n = document.createElement('button');

    //regole di stile CSS
    a.style.cssText = "width:"+w+"; height:"+h+"; border:1px solid #bbb; border-radius:5px; padding:10px; background:#9b334e; box-shadow:0px 0px 8px #0006; position:fixed; top:"+t+"; left:"+l+"; margin:auto; font-family: \"Arial\", sans-serif; color:black;z-index:"+ vis+ ";";
    input.style.cssText = "width:100%; margin-top:100px;";
    input.placeholder = "new "+message;
    if(message=="brand"){
      input.type="text";
      var x = 'autoBrand';
      input.setAttribute('list',x);
    }else if(message=="model"){
      input.type="text";
    }else if(message=="price"||message=="avg_consumption"){
      input.type="number";
      input.min="0";
      input.step="0.01";
    }else if(message=="car_type"){
      input.type="text"
      var x = 'autoTipologia';
      input.setAttribute('list',x);
    }else if(message=="doors"){
      input.type="number";
      input.min="0";
      var x = 'autoPorte';
      input.setAttribute('list',x);
    }else if(message=="transmission"){
      input.type="text";
      var x = 'autoCambio';
      input.setAttribute('list',x);
    }else if(message=="emission_class"){
      input.type="text";
      var x = 'Emision_class';
      input.setAttribute('list',x);
    }else if(message=="co2_emissions"||message=="capacity"||message=="horse_power"){
      input.type="number";
      input.min="0";
    }else if(message=="power_supply"){
      input.type="text";
      var x = 'autoPower';
      input.setAttribute('list',x);
    }else if(message=="img_car"){
      input.type="file";
      input.accept=".jpg,.png,.jpeg";
      input.maxLength="255";
      input.value="";
    }
    //buttons style
    y.style.cssText = "position:absolute; bottom:10; right:20px; width:40%; margin:2px; margin-bottom:10px; clear:both; border-bottom: 2px solid #dec717; background-color: #800000;";
    n.style.cssText = "position:absolute; bottom:10; left:20px; width:40%; margin:2px; margin-bottom:10px; clear:both; border-bottom: 2px solid #dec717; background-color: #800000;";
    a.innerHTML = "<b>Modifica "+message+"</b><br>";
    y.innerHTML = "Applica";
    n.innerHTML = "Annulla";
    document.body.appendChild(a);
    a.appendChild(input);
    a.appendChild(y);
    a.appendChild(n);
    vis--;

// case YES
    y.addEventListener("click", function(e) {
              var s=input.value;
              if(message=="img_car"){
                document.getElementById(message).setAttribute("value",s);
                readURL(input);
              }else {
                document.getElementById(message).setAttribute("value", s);

                document.getElementById(message + "L").replaceWith(s);
              }
              a.remove();
            }
    )
    //case NO
    n.addEventListener("click", function(e,resp) {
              a.remove();

            }
    )
  }

</script>

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
