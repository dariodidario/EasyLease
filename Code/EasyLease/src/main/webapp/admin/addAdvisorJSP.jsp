<%@ page import="java.awt.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String role=(String) request.getSession().getAttribute("role");
  if(role==null){%>
<html>
<head>
  <title>Add Advisor</title>
</head>
<body onload="window.location.href='fragments/error403.jsp'">

</body>
</html>
<%}else if(role.equalsIgnoreCase("admin")==false){ %>
<html>
<head>
  <title>Add Advisor</title>
</head>
<body onload="window.location.href='fragments/error403.jsp'">

</body>
</html>
<%}else {
%>
<%String error="";
  String s=(String)request.getSession().getAttribute("error");
  if(s!=null && !s.equalsIgnoreCase("")){
    error=s;
  }
%>
<html>
<head>
  <title>Add Advisor</title>
  <link rel="stylesheet" href="admin/addAdvisorCSS.css">
</head>

<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="admin/addAdvisorJS.js"></script>

<div id="header">
<%@include file="../fragments/headerJSP.jsp"%>
</div>
<div id="container">
<form action="AddAdvisorServlet">


  <table class="characteristics">
    <tr><td colspan="2" align="center"><label class="detailTitle">Aggiungi Consulente</label></td></tr>
    <tr><td colspan="2" align="center"><label id="errore"><%=error%></label></td></tr>
    <tr>
      <td><label class="advisorParameter">Nome</label></td>
      <td><input class="advisorParameter" type="text"  id="advisor_name" name="advisor_name" placeholder="es.(Mario)" pattern="^[a-zA-Z]+$" required></td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Cognome</label></td>
      <td><input class="advisorParameter" type="text" id="advisor_surname" name="advisor_surname" placeholder="es.(Rossi)" pattern="^[a-zA-Z]+$" required></td>
    </tr>
    <tr>
      <td><label class="advisorParameter">E-mail</label></td>
      <td><input class="advisorParameter" type="email" id="advisor_email" name="advisor_email" placeholder="es.(pippo@gmail.com)" onkeypress="checkEmail(this)" onchange="checkEmail(this)" required>
        <input type="hidden" id="email_valid" name="email_valid" value="false">
      </td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Data assunzione</label></td>
      <td><input class="advisorParameter" type="date" id="advisor_date" name="advisor_date" placeholder="es.(21/01/2021)" onchange="checkDate(this)" onkeypress="checkDate(this)" required>
        <input type="hidden" id="date_valid" name="date_valid" value="false">
      </td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Password</label></td>
      <td><input class="advisorParameter" type="password" id="advisor_password" name="advisor_password" placeholder="es.(marioR99)" onkeypress="checkPassword(this)"  required>
        <input type="hidden" id="password_valid" name="password_valid" value="false">
      </td>
    </tr>
    <tr>
      <td colspan="2"><p class="information">*la password deve essere lunga minimo 8 caretteri,
        deve contenere minimo una lettera Maiuscola, una minuscola, un numero</p> </td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Conferma Password</label></td>
      <td><input class="advisorParameter" type="password" id="advisor_confirm_password" name="advisor_confirm_password" placeholder="//" onkeypress="checkConfirm(this)"  required>
        <input type="hidden" id="confirm_valid" name="confirm_valid" value="false">
      </td>
    </tr>
  </table>

  <div id="divButton">
    <input type="submit" value="Aggiungi Consulente " id="buttonAddAdvisor">
  </div>
</form>
<hr>
</div>
<div id="footer">
<%@include file="../fragments/footerJSP.jsp"%>
</div>
</body>
</html>
<%}%>
