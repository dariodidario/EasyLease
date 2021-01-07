<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String role=(String) request.getSession().getAttribute("role");
  if(role==null){%>
<html>
<head>
  <title>Update Car</title>
</head>
<body onload="window.location.href='fragments/error403.jsp'">

</body>
</html>
<%}else if(role.equalsIgnoreCase("admin")==false){ %>
<html>
<head>
  <title>Update Car</title>
</head>
<body onload="window.location.href='fragments/error403.jsp'">

</body>
</html>
<%}else {
%>
<%String error="";
  String s=(String)request.getSession().getAttribute("error");
  if(s!=null){
    error=s;
  }
%>
<html>
<head>
  <title>Add Advisor</title>
</head>
<style type="text/css">
  hr{
    position: absolute;
    bottom: 20%;
    border: 1px solid #dec717;
    width: 70%;
    alignment: left;
    margin-left: 17%;
  }
  input.advisorParameter {
    width: 90%;
    background: #9b334e;
    font-size: 1vw;
  }
  input.advisorParameter:hover {
    background: #800000;
  }

  label.advisorParameter{
    font-size: 1.2vw;
  }
  .information{
    width: 100%;
    font-style: oblique;
    font-size: 0.9vw;
  }
  #errore{
    width: 100%;
    font-style: oblique;
    font-size: 1.1vw;
    color: #dec717;
  }

  table.characteristics{
    position: absolute;
    top: 20%;
    right: 35%;
    background: #9b334e;
    width: 30%;
    height: 50%;
    border: medium solid #dec717;
  }
  #buttonAddAdvisor:hover{
    background: #800000;
  }
  #buttonAddAdvisor{
    background: #9b334e;
    position: absolute;
    right: 43%;
    bottom: 23%;
    width: 15%;
    height: 5%;
    font-size: 1.5vw;
    border-bottom:medium solid #dec717;
    border-radius: 5px;
  }


</style>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>
  var confirm_valid;
  function checkEmail(input){
    var email=input.value;
    var email_valid =  /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{1,2})+$/;
    if(!email_valid.test(email)){
      document.getElementById("advisor_email").style.background="#dec717";
    }else{
      document.getElementById("advisor_email").style.background="#9b334e";
      document.getElementById("email_valid").setAttribute("value","true");
    }
  }
  function checkPassword(input){
    var password=input.value;
    var password_valid = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;
    if(!password_valid.test(password)){
      document.getElementById("advisor_password").style.background="#dec717";
    }else{
      confirm_valid=password;
      document.getElementById("advisor_password").style.background="#9b334e";
      document.getElementById("password_valid").setAttribute("value","true");
    }
  }
  function checkConfirm(input){
    var confirm=input.value;
    var label=document.createElement('label');
    label.id="error";
    if(confirm!=confirm_valid){
      document.getElementById("advisor_confirm_password").style.background="#dec717";
    }else{
      document.getElementById("advisor_confirm_password").style.background="#9b334e";
      document.getElementById("confirm_valid").setAttribute("value","true");
    }
  }
  function checkDate(input){
    var date=Date.parse(input.value);
    var date_valid=new Date();
    if(date<=date_valid){
      document.getElementById("advisor_date").style.background="#9b334e";
      document.getElementById("date_valid").setAttribute("value","true");
    }else{
      document.getElementById("advisor_date").style.background="#dec717";
    }
  }
</script>

<%@include file="../fragments/headerJSP.jsp"%>
<form action="AddAdvisorServlet">


  <div id="divButton">
    <input type="submit" value="Aggiungi Consulente" id="buttonAddAdvisor">
  </div>




  <table class="characteristics">
    <tr><td colspan="2" align="center"><h2>Aggiungi Consulente</h2></td></tr>
    <tr><td colspan="2" align="center"><label id="errore"><%=error%></label></td></tr>
    <tr>
      <td><label class="advisorParameter">Nome</label></td>
      <td><input class="advisorParameter" type="text"  id="advisor_name" name="advisor_name" placeholder="es.(Mario)" required></td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Cognome</label></td>
      <td><input class="advisorParameter" type="text" id="advisor_surname" name="advisor_surname" placeholder="es.(Rossi)" required></td>
    </tr>
    <tr>
      <td><label class="advisorParameter">E-mail</label></td>
      <td><input class="advisorParameter" type="email" id="advisor_email" name="advisor_email" placeholder="es.(pippo@gmail.com)" onkeypress="checkEmail(this)" required>
        <input type="hidden" id="email_valid" name="email_valid" value="false">
      </td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Data assunzione</label></td>
      <td><input class="advisorParameter" type="date" id="advisor_date" name="advisor_date" placeholder="es.(21/01/2021)" onchange="checkDate(this)" required>
        <input type="hidden" id="date_valid" name="date_valid" value="false">
      </td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Password</label></td>
      <td><input class="advisorParameter" type="password" id="advisor_password" name="advisor_password" placeholder="es.(marioR99)" onkeypress="checkPassword(this)" required>
        <input type="hidden" id="password_valid" name="password_valid" value="false">
      </td>
    </tr>
    <tr>
      <td colspan="2"><p class="information">*la password deve essere lunga minimo 8 caretteri,
        deve contenere minimo una lettera Maiuscola, una minuscola, un numero</p> </td>
    </tr>
    <tr>
      <td><label class="advisorParameter">Conferma Password</label></td>
      <td><input class="advisorParameter" type="password" id="advisor_confirm_password" name="advisor_confirm_password" placeholder="//" onkeypress="checkConfirm(this)" required>
        <input type="hidden" id="confirm_valid" name="confirm_valid" value="false">
      </td>
    </tr>
  </table>


</form>
<hr>
<%@include file="../fragments/footerJSP.jsp"%>
</body>
</html>
<%}%>
