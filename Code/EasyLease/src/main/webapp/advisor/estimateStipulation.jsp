<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.EasyLease.model.estimate.Estimate" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%
  if(request.getSession() == null){
    response.sendRedirect(request.getContextPath() + "/ViewLoginServlet");
  }
  Estimate estimate = (Estimate) request.getAttribute("estimate");
  if (estimate == null) {
    response.sendRedirect(request.getContextPath() + "/EstimateStipulationViewServlet");
    return;
  }
  List<Optional> optionalList = estimate.getOptionalList();
%>
<html>
<head>
  <title>Stipulazione Preventivi</title>
  <link rel="stylesheet" href="advisor/estimateStipulation.css">
</head>
<body>
<%@include file="/fragments/header.jsp"%>
<div class = "container">
  <table class="Estimate_main">
    <tr><td><label class="page_title"><%= estimate.getCar().getBrand() + " " + estimate.getCar().getModel()%></label></td></tr>
    <tr><td><img class="car_image" src="${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage()%>"></td></tr>
  </table>


  <form action = "${pageContext.request.contextPath}/EstimateStipulationServlet" method="post">
    <input type = "hidden" value="<%=estimate.getIdEstimate()%>", name="id">
    <table class="EstimatePrice">
      <tr>
        <td align="center"><label class="subtitle">Compila Scheda</label> </td>
      </tr>
      <%
        if (optionalList != null && optionalList.size() != 0) {
          Iterator<Optional> iterator = optionalList.iterator();
          while (iterator.hasNext()) {
            Optional optional = iterator.next();
      %>
      <tr>
        <td>
          <label class="Ltext"><%=optional.getOptionalName()%></label>
        </td>
      </tr>
      <tr>
        <td>
          <input type="number" class="estimate_price" id="<%=optional.getOptionalName()%>" name="<%=optional.getOptionalName()%>" min = "1", required>
          <label class="Ltext">€</label>
        </td>
      </tr>
      <%
          }
        }
      %>
      <tr>
        <td>
          <button type="submit" id="stipulate" class="estimate_button" role = "button" aria-pressed ="true">
            Conferma
          </button>
        </td>
      </tr>
    </table>
  </form>


</div>
<div><%@include file="/fragments/footer.jsp"%></div>
</body>
</html>
