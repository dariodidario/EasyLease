<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  Car car=(Car) request.getAttribute("car");
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
  <style>
    .btn-lg{
      background-color: #800000 !important;
      border: none !important;
    }

    .center {
      text-align: center;
    }

    .container{
      margin-top: 2% !important;
    }
    .car_name{
      font-size: 45px;
      font-weight: bold;
    }

    img, svg{
      max-width: 700px !important;
      max-height: 700px !important;
      margin: auto !important;
      display: block !important;
    }

    .car_spec_text{
      font-size: 45px;
      font-weight: bold;
    }

    .car_spec td{
      font-size: 30px;
    }


    .car_optionals td{
      font-size: 30px;
    }

    .car_optionals th{
      font-size: 30px;
    }

    .btn-check:active+.btn-primary, .btn-check:checked+.btn-primary,
     .btn-primary:active, .show>{
      background-color: #9B334E !important;
      border-color: #9B334E !important;

    }


    body {
      display: flex;
      min-height: 100vh;
      flex-direction: column;
    }

    .Site-content {
      flex: 1;
    }


    .responsive {
      width: 100%;
      height: auto;
    }
  </style>
</head>
<body>
<%@include file="../fragments/headerJSP.jsp"%>
<div class="center Site-content">
  <%if(request.getSession().getAttribute("role")==null){%>
  <div class="container w-full">
    <div class="row">
      <div class="col-6">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>

    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" class="responsive" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="${pageContext.request.contextPath}/ViewLoginServlet">
          <input type="submit" class="btn btn-primary btn-lg" name="Registrati" value="Richiedi Preventivo">
        </form>
      </div>
      <div class="col ms-6" style="border: solid;">
          <div class="car_spec_text">
            Informazioni auto
        </div>
        <br><br>
        <div class="row">
          <div class="col">
            <h4>Porte</h4>
          </div>
          <div class="col">
            <h4><%=car.getDoors()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Tipo</h4>
          </div>
          <div class="col">
            <h4><%=car.getType()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Trasmissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getTransmission()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Consumo medio</h4>
          </div>
          <div class="col">
            <h4><%=car.getAvg_consumption()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cavalli</h4>
          </div>
          <div class="col">
            <h4><%=car.getHorse_power()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Classe di emissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getEmission_class()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Emissione CO2</h4>
          </div>
          <div class="col">
            <h4><%=car.getCo2_emissions()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Alimentazione</h4>
          </div>
          <div class="col">
            <h4><%=car.getPowerSupply()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cilindrata</h4>
          </div>
          <div class="col">
            <h4><%=car.getCapacity()%>
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>


  <%}else{ if(request.getSession().getAttribute("role").equals("admin")){ %>
  <div class="container w-full">
    <div class="row">
      <div class="col-6">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" class="responsive" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="${pageContext.request.contextPath}/ViewUpdateCarServlet">
          <input type="hidden" name="Car_id" value="<%=car.getId()%>">
          <input type="submit" class="btn btn-primary btn-lg" name="Modifica Auto" value="Modifica">
        </form>
        <br>
        <form method="POST" action="${pageContext.request.contextPath}/DeleteCarServlet">
          <input type="hidden" name="ID_Delete" value="<%=car.getId()%>">
          <input type="submit" class="btn btn-primary btn-lg" name="Elimina Auto" value="Elimina Auto">
        </form>
      </div>
      <div class="col ms-6" style="border: solid;">
        <div class="car_spec_text">
          Informazioni auto
        </div>
        <br><br>
        <div class="row">
          <div class="col">
            <h4>Porte</h4>
          </div>
          <div class="col">
            <h4><%=car.getDoors()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Tipo</h4>
          </div>
          <div class="col">
            <h4><%=car.getType()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Trasmissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getTransmission()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Consumo medio</h4>
          </div>
          <div class="col">
            <h4><%=car.getAvg_consumption()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cavalli</h4>
          </div>
          <div class="col">
            <h4><%=car.getHorse_power()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Classe di emissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getEmission_class()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Emissione CO2</h4>
          </div>
          <div class="col">
            <h4><%=car.getCo2_emissions()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Alimentazione</h4>
          </div>
          <div class="col">
            <h4><%=car.getPowerSupply()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cilindrata</h4>
          </div>
          <div class="col">
            <h4><%=car.getCapacity()%>
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%}else{ if(request.getSession().getAttribute("role").equals("client")){ %>

  <div class="container w-full">
    <div class="row">
      <div class="col-6">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" class="responsive" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="${pageContext.request.contextPath}/ViewRequestEstimateServlet">
          <input type="hidden" name="idCar" value="<%=car.getId()%>">
          <input type="submit" class="btn btn-primary btn-lg" name="Richiedi preventivo" value="Richiedi preventivo">
        </form>
      </div>
      <div class="col ms-6" style="border: solid;">
        <div class="car_spec_text">
          Informazioni auto
        </div>
        <br><br>
        <div class="row">
          <div class="col">
            <h4>Porte</h4>
          </div>
          <div class="col">
            <h4><%=car.getDoors()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Tipo</h4>
          </div>
          <div class="col">
            <h4><%=car.getType()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Trasmissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getTransmission()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Consumo medio</h4>
          </div>
          <div class="col">
            <h4><%=car.getAvg_consumption()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cavalli</h4>
          </div>
          <div class="col">
            <h4><%=car.getHorse_power()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Classe di emissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getEmission_class()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Emissione CO2</h4>
          </div>
          <div class="col">
            <h4><%=car.getCo2_emissions()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Alimentazione</h4>
          </div>
          <div class="col">
            <h4><%=car.getPowerSupply()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cilindrata</h4>
          </div>
          <div class="col">
            <h4><%=car.getCapacity()%>
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>

  <%}else{ if(request.getSession().getAttribute("role").equals("advisor")){ %>

  <div class="container w-full">
    <div class="row">
      <div class="col-6">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" class="responsive" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="./homePageJSP.jsp">
          <input type="submit" class="btn btn-primary btn-lg" name="Home" value="Home" >
        </form>
      </div>
      <div class="col ms-6" style="border: solid;">
        <div class="car_spec_text">
          Informazioni auto
        </div>
        <br><br>
        <div class="row">
          <div class="col">
            <h4>Porte</h4>
          </div>
          <div class="col">
            <h4><%=car.getDoors()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Tipo</h4>
          </div>
          <div class="col">
            <h4><%=car.getType()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Trasmissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getTransmission()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Consumo medio</h4>
          </div>
          <div class="col">
            <h4><%=car.getAvg_consumption()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cavalli</h4>
          </div>
          <div class="col">
            <h4><%=car.getHorse_power()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Classe di emissione</h4>
          </div>
          <div class="col">
            <h4><%=car.getEmission_class()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Emissione CO2</h4>
          </div>
          <div class="col">
            <h4><%=car.getCo2_emissions()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Alimentazione</h4>
          </div>
          <div class="col">
            <h4><%=car.getPowerSupply()%>
            </h4>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h4>Cilindrata</h4>
          </div>
          <div class="col">
            <h4><%=car.getCapacity()%>
            </h4>
          </div>
        </div>
      </div>
    </div>
  </div>

  <%}}}}%>
</div>

<footer><%@include file="../fragments/footerJSP.jsp"%></footer>

</body>
</html>