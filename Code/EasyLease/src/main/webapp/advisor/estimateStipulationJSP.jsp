<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.EasyLease.model.estimate.Estimate" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%
  if(request.getSession() == null){
    response.sendRedirect(request.getContextPath() + "/LoginViewServlet");
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
  <title>Estimate Management Advisor</title>
  <link rel = "stylesheet" href = "${pageContext.request.contextPath}/advisor/estimateStipulationJSP.css"/>
</head>
<body>
<%@include file="/fragments/headerJSP.jsp"%>
<div class = "container">
  <div class = "row mt-3">
    <div class = "col md-5">
      <div class = "car_name">
        <h2><%= estimate.getCar().getBrand() + " " + estimate.getCar().getModel()%></h2>
      </div>
      <div>
        <img src = "${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage()%>">
      </div>
    </div>
    <div class = "col mt-5 md-5">
      <form action = "${pageContext.request.contextPath}/EstimateStipulationServlet" method="post">
        <div class="mb-3 ms-5">
          <%
            if (optionalList != null && optionalList.size() != 0) {
              Iterator<Optional> iterator = optionalList.iterator();
              while (iterator.hasNext()) {
                Optional optional = iterator.next();
          %>
          <div class = "w-5">
            <label for = "<%=optional.getName()%>" class = "form-label mt-3"><%=optional.getName()%></label>
            <input type = "number" class="form-control px-2" id="<%=optional.getName()%>"
                   name="<%=optional.getName()%>" min = "1", required>
          </div>

            <%
              }
              }
            %>
            <input type = "hidden" value="<%=estimate.getId()%>", name="id">
        <button type="submit" class="btn btn-primary mt-3 btn-lg active" role = "button" aria-pressed ="true">
          Conferma
        </button>
      </form>
    </div>
  </div>
</div>
</div>
<div><%@include file="/fragments/footerJSP.jsp"%></div>
</body>
</html>
