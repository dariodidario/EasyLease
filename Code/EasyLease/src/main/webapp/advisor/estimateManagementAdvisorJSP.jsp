<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.EasyLease.model.estimate.Estimate" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
  Estimate estimate = (Estimate) request.getAttribute("estimate");
  ArrayList<Optional> caroptionals = new ArrayList<Optional>();
  ArrayList<Optional> contractoptionals = new ArrayList<Optional>();
  if(estimate != null){
    for(Optional o : estimate.getOptionalList()){
      if(o.getType().equals("Auto"))
        caroptionals.add(o);
      else if (o.getType().equals("Contratto"))
        contractoptionals.add(o);
    }
  }
%>
<html>
<head>
  <title>Estimate Management Advisor</title>
  <link rel = "stylesheet" href = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
  <link rel = "stylesheet" href = "${pageContext.request.contextPath}/advisor/estimateManagementAdvisorJSP.css"/>
</head>
<body>
<div class = "container w-full">
  <div class = "row">
    <div class = "col-6" align = "center">
      <div class = "car_name">
        <%= estimate.getCar().getBrand() + " " + estimate.getCar().getModel()%>
      </div>
    </div>
    <div class = "col-6" align = "center">
      <div>
        <h2>Informazioni auto</h2>
      </div>
    </div>
  </div>
  <div class = "row">
    <div class = "col ms-6">
      <img src = "${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage()%>">
    </div>
    <div class = "col ms-6" align = "center">
      <div class = "row">
        <div class = "col">
          <h4>Porte</h4>
        </div>
        <div class = "col">
          <h4> <%=estimate.getCar().getDoors()%></h4>
        </div>
      </div>
      <div class = "row">
        <div class = "col">
          <h4>Cambio</h4>
        </div>
        <div class = "col">
          <h4> <%=estimate.getCar().getTransmission()%></h4>
        </div>
      </div>
      <div class = "row">
        <div class = "col">
          <h4>Cavalli</h4>
        </div>
        <div class = "col">
          <h4> <%=estimate.getCar().getHorse_power()%></h4>
        </div>
      </div>
      <div class = "row">
        <div class = "col">
          <h4>Classe di emissioni</h4>
        </div>
        <div class = "col">
          <h4> <%=estimate.getCar().getEmission_class()%></h4>
        </div>
      </div>
      <div class = "row">
        <div class = "col">
          <h4>Alimentazione</h4>
        </div>
        <div class = "col">
          <h4> <%=estimate.getCar().getPowerSupply()%></h4>
        </div>
      </div>
      <div class = "row">
        <div class = "col">
          <h4>Cambio</h4>
        </div>
        <div class = "col">
          <h4> <%=estimate.getCar().getCapacity()%></h4>
        </div>
      </div>
      <div class = "car_optional_text">Optional</div>
      <table class = "car_optionals">
        <%
          if(caroptionals.size() > 0 || contractoptionals.size() > 0)
        %>
        <thead>
          <tr>
            <th scope = "col">Nome</th>
            <th scope = "col">Costo</th>
            <th scope = "col">Tipo</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <%
              if(caroptionals.size() > 0){
                Iterator<Optional> carIterator = caroptionals.iterator();
                while(carIterator.hasNext()){
                  Optional carOptional = carIterator.next();
            %>
            <td data-th = "Nome"><%=carOptional.getName()%></td>
            <td data-th = "Costo"><%=carOptional.getPrice()%></td>
            <td data-th = "Tipo"><%=carOptional.getType()%></td>
          </tr>
          <tr>
            <%
                }
              }
              if(contractoptionals.size() > 0){
                Iterator<Optional> contractIterator = contractoptionals.iterator();
                while(contractIterator.hasNext()){
                  Optional contractOptional = contractIterator.next();
            %>
            <td data-th = "Nome"><%=contractOptional.getName()%></td>
            <td data-th = "Costo"><%=contractOptional.getPrice()%></td>
            <td data-th = "Tipo"><%=contractOptional.getType()%></td>
          </tr>
        </tbody>
        <%
            }
          } else {
        %>
        <div class = "no_optionals_text" colspan = "6"> Nussun optional selezionato</div>
        <%
          }
        %>
      </table>
    </div>
  </div>
  <div class = "row">
    <div class = "col md-6" align = "center">
      <div class = "order_status">
        <%
          if(estimate.getPrice() >0){
        %>
          <h2>Prezzo totale</h2>
          <h2 class = "price">
        <%=String.format("%.2f", estimate.getPrice()) + "€"%>
        </h2>
        <h2>Prezzo mensile</h2>
        <h2 class = "price">
          <%=String.format("%.2f", estimate.getPrice()/estimate.getPeriod()) + "€"%>
        </h2>
        <%
           } else {
        %>
        <h2>Prezzo non disponibile</h2>
        <%
          }
        %>
      </div>
      <%
        if(estimate.getState().equals("Preso in carico")){
      %>
      <div class = "row">
        <div class = "col-12">
          <a href = "EstimateStipulationViewServlet?id=<%=estimate.getId()%>"
             class = "btn btn-primary btn-lg active" role = "button" aria-pressed ="true">Stipula</a>
        </div>
      </div>
      <%
        }
      %>
    </div>
  </div>
</div>
</body>
</html>
