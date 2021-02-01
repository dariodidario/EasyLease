<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.EasyLease.model.order.Order" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>


<%
  if(request.getSession() == null){
    response.sendRedirect(request.getContextPath() + "/ViewLoginServlet");
  }
  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
  Order order = (Order) request.getAttribute("order");
  if (order == null) {
    response.sendRedirect(request.getContextPath() + "/OrderValidationViewServlet");
    return;
  }
  ArrayList<Optional> caroptionals = new ArrayList<Optional>();
  ArrayList<Optional> contractoptionals = new ArrayList<Optional>();
  for(Optional o : order.getEstimate().getOptionalList()){
    if(o.getOptionalType().equals("Auto"))
      caroptionals.add(o);
    else if (o.getOptionalType().equals("Contratto"))
      contractoptionals.add(o);
  }
%>
<html>
<head>
  <title>Approva Ordini</title>
  <link rel="stylesheet" href="advisor/orderValidation.css">
</head>
<body>
<div><%@include file="/fragments/header.jsp"%></div>
<div class = "container">
  <table class="Contract_main">
    <tr><td><label class="page_title"><%= order.getEstimate().getCar().getBrand() + " " + order.getEstimate().getCar().getModel()%></label></td></tr>
    <tr><td><img class="car_image" src="${pageContext.request.contextPath}/img/<%=order.getEstimate().getCar().getImage()%>"></td></tr>
  </table>







  <div class="Contract_information">
    <br>
    <table class="car_information" align="center">
      <tr>
        <td colspan="5" align="center"><label class="subtitle">Informazioni auto</label></td>
      </tr>
      <tr>
        <td><label class="Ltext">ID Ordine</label></td>
        <td><label class="Ltext">Cliente</label></td>
        <td><label class="Ltext">Data Inizio</label></td>
        <td><label class="Ltext">Periodo</label></td>
        <td><label class="Ltext">Stato</label></td>
      </tr>
      <tr>
        <td><label class="Ltext"><%=order.getIdOrder()%></label></td>
        <td><label class="Ltext"><%=order.getEstimate().getClient().getFirstName() + " " + order.getEstimate().getClient().getSurname()%></label></td>
        <td><label class="Ltext"><%=order.getStartDate() != null ? format.format(order.getStartDate()) : "Data non disponibile"%></label></td>
        <td><label class="Ltext"><%=order.getEstimate().getPeriod() + " mesi"%></label></td>
        <td><label class="Ltext"><%=order.getState()%></label></td>
      </tr>
    </table>




    <br>
    <table class="car_optionals">
      <tr>
        <td colspan="3" align="center">
          <label class="subtitle">Optional</label>
        </td>
      </tr>
      <tr>
        <td><label class="Ltext">Nome</label></td>
        <td><label class="Ltext">Costo</label></td>
        <td><label class="Ltext">Tipo</label></td>
      </tr>
      <%
        if(caroptionals.size() > 0){
          Iterator<Optional> carIterator = caroptionals.iterator();
          while(carIterator.hasNext()){
            Optional carOptional = carIterator.next();
      %>
      <tr>
        <td><label class="Ltext"><%=carOptional.getOptionalName()%></label></td>
        <td><label class="Ltext"><%=carOptional.getPrice()%></label></td>
        <td><label class="Ltext"><%=carOptional.getOptionalType()%></label></td>
      </tr>
      <%
          }
        }
        if(contractoptionals.size() > 0){
          Iterator<Optional> contractIterator = contractoptionals.iterator();
          while(contractIterator.hasNext()){
            Optional contractOptional = contractIterator.next();
      %>
      <tr>
        <td><label class="Ltext"><%=contractOptional.getOptionalName()%></label></td>
        <td><label class="Ltext"><%=contractOptional.getPrice()%></label></td>
        <td><label class="Ltext"><%=contractOptional.getOptionalType()%></label></td>
      </tr>
      <%
        }
      } else {
      %>
      <tr><td colspan="3" align="center"><label class="Etext">Nussun optional selezionato</label></td></tr>
      <%
        }
      %>
    </table>
  </div>


  <table class="Contract_price" align="center">
    <%
      if(order.getEstimate().getPrice() >0){
    %>
    <tr>
      <td align="center"><label class="subtitle">Prezzo totale</label></td>
    </tr>
    <tr>
      <td align="center"><label class="Ltext"><%=String.format("%.2f", order.getEstimate().getPrice()) + "€"%></label></td>
    </tr>
    <tr>
      <td align="center"><label class="subtitle">Prezzo mensile</label></td>
    </tr>
    <tr>
      <td align="center"><label class="Ltext"><%=String.format("%.2f", order.getEstimate().getPrice()/order.getEstimate().getPeriod()) + "€"%></label></td>
    </tr>
    <%
    } else {
    %>
    <tr><td align="center"><label class="Etext">Prezzo non disponibile</label></td></tr>
    <%
      }
    %>
    <tr>
      <td>
        <%
          if(order.getState().equals("Pagato")){
        %>
        <form action = "${pageContext.request.contextPath}/OrderValidationServlet" method="post">
          <input type = "hidden" value="<%=order.getIdOrder()%>", name="id">
          <label for = "date" class = "Contract_confirm">Data di ritiro</label>
          <input type = "date" class= "confirm_date" id="date" name = "date" min="<%= new Date()%>" required>
          <button type="submit" class="confirm_button" role = "button" aria-pressed ="true">
            Conferma
          </button>
        </form>
        <%
          }
        %>
      </td>
    </tr>
  </table>


</div>
<div><%@include file="/fragments/footer.jsp"%></div>
</body>
</html>
