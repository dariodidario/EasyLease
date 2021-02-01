<%@ page import="com.easylease.EasyLease.model.car.Car" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.List" %>
<%@ page import="com.easylease.EasyLease.model.optional.DbOptionalDao" %>
<%@ page import="com.easylease.EasyLease.model.car.DbCarDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.easylease.EasyLease.model.client.DbClientDao" %>
<%@ page import="com.easylease.EasyLease.model.client.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   String role = (String)request.getSession().getAttribute("role");
   if (!role.equals("client")) {
       response.sendRedirect(((HttpServletRequest)request).getContextPath()+"/HomeServlet");
   }
   Client client = (Client)request.getSession().getAttribute("user");
   Car car = (Car)request.getSession().getAttribute("car");
   List<Optional> optionalCarList = (List<Optional>)request.getSession().getAttribute("optionalCarList");
   List<Optional> optionalContractList = (List<Optional>)request.getSession().getAttribute("optionalContractList");

%>
<%@include file="../fragments/header.jsp"%>
<html>
<head>
  <title>Richiedi un preventivo</title>
  <link href="${pageContext.request.contextPath}/client/requestEstimate.css" rel="stylesheet">
</head>
<body>

<div class="RequestEstimateForm">
    <img src="${pageContext.request.contextPath}/img/misc/logo4%20(1).png" class="logo">
    <div class="EstimateLabel">
        Auto scelta: <%=car.getBrand() + " " + car.getModel()%> </div>
    <form action="${pageContext.request.contextPath}/RequestEstimateServlet" method="post">
        <input type = "hidden" name="carId" value="<%=car.getIdCar()%>">
        <br>Mesi: <select name="Mesi" class="customselect">
            <option value="18" selected>18</option>
            <option value="24">24</option>
            <option value="36">36</option>
            <option value="48">48</option>
            <option value="60">60</option>
            <option value="72">72</option>
         </select><br>
        <div class="EstimateLabel"> <br> Optional di contratto:</div><br>
        <%for (Optional o : optionalContractList){%>
        <input type="checkbox" name="optionals" value=<%=o.getOptionalCode()%> class="EstimateCheckbox">
        <label class="EstimateLabel"> <%=o.getOptionalName()%></label><br><br>
        <%}%><br>
        <div class="EstimateLabel"><br> Optional di serie:</div><br>
        <%for (Optional o : optionalCarList){%>
        <input type="checkbox" name="optionals" value=<%=o.getOptionalCode()%> class="EstimateCheckbox">
        <label class="EstimateLabel"> <%=o.getOptionalName()%></label><br><br>
        <%}%><br>

        <br><input type=submit name=submit value="Aggiungi Prodotto" class="EstimateButton">
    </form>
</div>
<%@include file="/fragments/footer.jsp"%>
</body>
</html>
