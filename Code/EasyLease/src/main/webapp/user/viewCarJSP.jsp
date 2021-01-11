<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  Car car=(Car)request.getAttribute("car");
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
<%@include file="../fragments/headerJSP.jsp"%>
<div class="center">
  <%if(request.getSession().getAttribute("role")==null){%>
  <div class="container w-full">
    <div class="row">
      <div class="col-6" align="center">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>
      <div class="col-6" align="center">
        <div class="car_spec_text">
          Informazioni auto
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="../client/signInJSP.jsp">
          <input type="submit" class="btn btn-primary btn-lg" name="Registrati" value="Registrati">
        </form>
      </div>
      <div class="col ms-6" align="center" style="background-color: lightgray; border: solid;">
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
            <h4><%=car.getTransmision()%>
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
      <div class="col-6" align="center">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>
      <div class="col-6" align="center">
        <div class="car_spec_text">
          Informazioni auto
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="../admin/updateCarJSP.jsp">
          <input type="submit" class="btn btn-primary btn-lg" name="Modifica Auto" value="Modifica">
        </form>
        <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.admin.DeleteCarServlet?id=<%=car.getId()%>">
          <input type="submit" class="btn btn-primary btn-lg" name="Elimina Auto" value="Elimina Auto">
        </form>
      </div>
      <div class="col ms-6" align="center" style="background-color: lightgray; border: solid;">
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
            <h4><%=car.getTransmision()%>
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
      <div class="col-6" align="center">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>
      <div class="col-6" align="center">
        <div class="car_spec_text">
          Informazioni auto
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="jetbrains://idea/navigate/reference?project=EasyLease&path=advisor/estimateManagementAdvisorJSP.jsp?id=<%=car.getId()%>">
          <input type="submit" class="btn btn-primary btn-lg" name="Richiedi preventivo" value="Richiedi preventivo">
        </form>
      </div>
      <div class="col ms-6" align="center" style="background-color: lightgray; border: solid;">
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
            <h4><%=car.getTransmision()%>
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
      <div class="col-6" align="center">
        <div class="car_name">
          <%= "" + car.getBrand() + " "
                  + car.getModel()%>
        </div>
      </div>
      <div class="col-6" align="center">
        <div class="car_spec_text">
          Informazioni auto
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col me-6">
        <img alt="" src="${pageContext.request.contextPath}/img/<%=car.getImage()%>">

        <br/>
        <br/>
        <form method="POST" action="../index.jsp">
          <input type="submit" class="btn btn-primary btn-lg" name="Home" value="Home" >
        </form>
      </div>
      <div class="col ms-6" align="center" style="background-color: lightgray; border: solid;">
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
            <h4><%=car.getTransmision()%>
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
<%@include file="../fragments/footerJSP.jsp"%>
</body>
</html>