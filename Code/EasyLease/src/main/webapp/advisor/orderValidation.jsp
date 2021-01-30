<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.EasyLease.model.order.Order" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>


<%
  if(request.getSession() == null){
    response.sendRedirect(request.getContextPath() + "/LoginViewServlet");
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
    if(o.getType().equals("Auto"))
      caroptionals.add(o);
    else if (o.getType().equals("Contratto"))
      contractoptionals.add(o);
  }
%>
<html>
<head>
  <title>Estimate Management Advisor</title>
  <link rel = "stylesheet" href = "${pageContext.request.contextPath}/advisor/estimateManagementAdvisor.css"/>
</head>
<body>
<%@include file="/fragments/header.jsp"%>
<div class = "container w-full">
  <div class = "row">
    <div class = "col-6" align = "center">
      <div class = "car_name">
        <%= order.getEstimate().getCar().getBrand() + " " + order.getEstimate().getCar().getModel()%>
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
      <img src = "${pageContext.request.contextPath}/img/<%=order.getEstimate().getCar().getImage()%>">
    </div>
    <div class = "col ms-6" align = "center">
      <div class = "row">
        <div class = "col">
          <h4>ID Ordine</h4>
        </div>
        <div class = "col">
          <h4><%=order.getId()%>
        </div>
        <div class = "row">
          <div class = "col">
            <h4>Cliente</h4>
          </div>
          <div class = "col">
            <h4><%=order.getEstimate().getClient().getName() + " " + order.getEstimate().getClient().getSurname() %></h4>
          </div>
        </div>
        <div class = "row">
          <div class = "col">
            <h4>Data Inizio</h4>
          </div>
          <div class = "col">
            <h4><%=order.getStartDate() != null ? format.format(order.getStartDate()) : "Data non disponibile" %> </h4>
          </div>
        </div>
        <div class = "row">
          <div class = "col">
            <h4>Periodo</h4>
          </div>
          <div class = "col">
            <h4><%=order.getEstimate().getPeriod() + " mesi"%>
          </div>
        </div>
        <div class = "row">
          <div class = "col">
            <h4>Stato</h4>
          </div>
          <div class = "col">
            <h4><%=order.getState()%>
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
            if(order.getEstimate().getPrice() >0){
          %>
          <h2>Prezzo totale</h2>
          <h2 class = "price">
            <%=String.format("%.2f", order.getEstimate().getPrice()) + "€"%>
          </h2>
          <h2>Prezzo mensile</h2>
          <h2 class = "price">
            <%=String.format("%.2f", order.getEstimate().getPrice()/order.getEstimate().getPeriod()) + "€"%>
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
          if(order.getState().equals("Pagato")){
        %>
        <div class = "row">
          <form action = "${pageContext.request.contextPath}/OrderValidationServlet" method="post">
            <div class = "w-2">
            <div class = "mb-3 ms-5">
              <label for = "date" class = "form-label mt-3">Data di ritiro</label>
              <input type = "date" class= "form-control" id="date" name = "date" min="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" required>
            </div>
            <input type = "hidden" value="<%=order.getId()%>", name="id">
              <input type = "hidden" value="<%="true"%>", name="choice">
            <button type="submit" class="btn btn-primary mt-3 btn-lg active" role = "button" aria-pressed ="true" id="validation">
              Conferma
            </button>
            </div>
          </form>
        </div>
        <%
          }
        %>
      </div>
    </div>
  </div>
</div>
<div><%@include file="/fragments/footer.jsp"%></div>
</body>
</html>
