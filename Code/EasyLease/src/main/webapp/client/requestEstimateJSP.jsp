<%@ page import="com.easylease.EasyLease.model.car.Car" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.List" %>
<%@ page import="com.easylease.EasyLease.model.optional.DBOptionalDAO" %>
<%@ page import="com.easylease.EasyLease.model.car.DBCarDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   /* String idCar = request.getParameter("idCar");*/
    request.setAttribute("idCar", "CAbj0kk");
    Car car = DBCarDAO.getInstance().retriveById("CAbj0kk");
    List<Optional> optionalCarList = DBOptionalDAO.getInstance().retrieveByType("Auto");
    List<Optional> optionalContractList = DBOptionalDAO.getInstance().retrieveByType("Contratto");
%>
<%@include file="../fragments/headerJSP.jsp"%>
<html>
<head>
  <title>Richiedi un preventivo</title>
</head>
<body>
<link href="./requestEstimateJSP.css" rel="stylesheet">

<div class="RequestEstimateForm">
    <img src="../foto/newLogo.png" class="logo">
    <div class="EstimateLabel">
        Auto scelta: <%=car.getBrand() + " " + car.getModel()%> </div>
    <form action='RequestEstimateServlet' method="post">
        <br>Mesi: <select name="Mesi" class="customselect">
            <option value="24" selected>24</option>
            <option value="36">36</option>
            <option value="48">48</option>
            <option value="60">60</option>
            <option value="72">72</option>
         </select><br>
        <div class="EstimateLabel"> <br> Optional di contratto:</div><br>
        <%for (Optional o : optionalContractList){%>
        <input type="checkbox" name="optionals" value=<%=o.getId()%> class="EstimateCheckbox">
        <label class="EstimateLabel"> <%=o.getName()%></label><br><br>
        <%}%><br>
        <div class="EstimateLabel"><br> Optional di serie:</div><br>
        <%for (Optional o : optionalCarList){%>
        <input type="checkbox" name="optionals" value=<%=o.getId()%> class="EstimateCheckbox">
        <label class="EstimateLabel"> <%=o.getName()%></label><br><br>
        <%}%><br>

        <br><input type=submit name=submit value="Aggiungi Prodotto" class="EstimateButton">
    </form>
</div>
</body>
<%@include file="../fragments/footerJSP.jsp"%>
</html>
