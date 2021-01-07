<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<html>
<head>
  <title>Add Car</title>
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
  input.carDetail {
    width: 40%;
    background: #9b334e;
    font-size: 0.8vw;
  }
  input.carDetail:hover {
    background: #800000;
  }

  label.carDetail{
    font-size: 1.5vw;
  }
  input.autoName{
    width: 15%;
    background: none;
    border: none;
    border-bottom: solid #dec717;
  }

  table.characteristics{
    position: absolute;
    top: 20.5%;
    right: 1%;
    background: #9b334e;
    width: 30%;
    height: 59%;
    border-right: medium solid #dec717;
    border-top: medium solid #dec717;
    border-bottom: medium solid #dec717;
  }
  #buttonAddCar:hover{
    background: #800000;
  }
  #buttonAddCar{
    background: #9b334e;
    position: absolute;
    left: 30%;
    top: 72%;
    width: 15%;
    height: 5%;
    font-size: 1.5vw;
    border-bottom:medium solid #dec717;
    border-radius: 5px;
  }
  #divAutoName{
    position: absolute;
    top: 20.5%;
    left: 5%;
    width: 64%;
  }
  #divImage{
    top: 30%;
    position: absolute;
    left: 5%;
    width: 64%;
  }
  #img_prev{
    margin-top: 5px;
    max-width: 30%;
    max-height: 20%;
    position: relative;
    left: 5%;
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
        var img='#img_prev';
        reader.onload = function (e) {
          $(img)
                  .attr('src', e.target.result)
        };

        reader.readAsDataURL(input.files[i]);

      }
    }
  }


</script>

<%@include file="../fragments/headerJSP.jsp"%>
<form action="AddCarServlet" method="post" enctype="multipart/form-data">

  <div id="divAutoName">
    <label for="brand">Marca:</label>

    <input type="text"  class="autoName" id="brand" name="brand" placeholder="es.(ferrari)" list="autoBrand" required>
    <label for="model">Modello:</label>
    <input class="autoName" type="text" id="model" name="model" placeholder="es.(california)"  required>
  </div>


  <div id="divImage">
    <label>Foto Auto</label>
    <br>
    <img id="img_prev" src="#" alt="your image" />
    <br>
    <input id="image_path" name="image_path" type='file' value="" maxlength="255" onchange="readURL(this); " accept=".jpg" required/>
  </div>

  <div id="divButton">
    <input type="submit" value="Aggiungi Auto" id="buttonAddCar">
  </div>




  <table class="characteristics">
    <tr><td colspan="3" align="center"><h2>Caratteristiche</h2></td></tr>
    <tr>
      <td><label class="carDetail">Tipologia</label></td>
      <td><input class="carDetail" type="text" min="0" id="car_type" name="car_type" list="autoTipologia" required></td>
    </tr>
    <tr>
      <td><label class="carDetail">Porte</label></td>
      <td><input class="carDetail" type="number" min="0" id="doors" name="doors" list="autoPorte" required></td>
    </tr>
    <tr>
      <td><label class="carDetail">Cambio</label></td>
      <td><input class="carDetail" type="text" id="transmission" name="transmission" list="autoCambio" required></td>
    </tr>
    <tr>
      <td><label class="carDetail">Consumo medio</label></td>
      <td><input class="carDetail" type="number" min="0" step="0.01" id="avg_consumption" name="avg_consumption" placeholder="es.(3.9)" required>
        <label class="carDetail">L/100km</label>
      </td>
    </tr>
    <tr>
      <td><label class="carDetail">Cavalli</label></td>
      <td><input class="carDetail" type="number" min="0" id="horses" name="horse_power" placeholder="es.(130)" required>
        <label class="carDetail">CV</label>
      </td>
    </tr>
    <tr>
      <td><label class="carDetail">CO&sup2;</label></td>
      <td><input class="carDetail" type="text" id="emission_class" name="emission_class" list="Emision_class" required></td>
    </tr>
    <tr>
      <td><label class="carDetail">Emissioni di CO&sup2;</label></td>
      <td><input class="carDetail" type="number" min="0" id="co2_emissions" name="co2_emissions" placeholder="es.(104)" required>
        <label class="carDetail">g/km</label>
      </td>
    </tr>
    <tr>
      <td><label class="carDetail">Alimentazione</label></td>
      <td><input class="carDetail" type="text" id="power_supply" name="power_supply" list="autoPower" required></td>
    </tr>
    <tr>
      <td><label class="carDetail">Cilindrata Motore</label></td>
      <td><input class="carDetail" type="number" min="0" id="capacity" name="capacity" placeholder="es.(1499)" required>
        <label class="carDetail">cm&sup3;</label>
      </td>
    </tr>
    <tr>
      <td><label class="carDetail">Price</label></td>
      <td><input class="carDetail" type="number" min="0" step="0.01" id="price" name="price" placeholder="es.(5)" required>
        <label class="carDetail">&euro;/mese</label>
      </td>
    </tr>
  </table>


</form>
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
