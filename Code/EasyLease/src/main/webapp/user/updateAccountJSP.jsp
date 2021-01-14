<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  Client cliente = new Client("tring id", "String name", "String surname", "francesco@torino.it",
          "String birthPlace", null, "Uomo",
          "String city", "String pc", "String street");
  //Client cliente=(Client)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous">
<link rel="stylesheet" href="update.css">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.easylease.EasyLease.model.client.Client"%>
<head>
  <meta charset="ISO-8859-1">
  <title>Aggiorna Account</title>
  <style>
    .btn-primary{
      background-color: #800000 !important;
    }
  </style>
</head>
<body>
<%@include file="../fragments/headerJSP.jsp"%>
<script type="text/javascript">
  function controllo(){
    var controllo=true;
    var mail=document.getElementById("email").value;
    var nome=document.getElementById("nome").value;
    var password=document.getElementById("password").value;
    var cognome=document.getElementById("cognome").value;
    var bd=document.getElementById("bd").value;
    var bp=document.getElementById("bp").value;
    var kind=document.getElementById("kind").value;
    var via=document.getElementById("via").value;
    var cap=document.getElementById("cap").value;
    var citta=document.getElementById("citta").value;
    var conferma=document.getElementById("conferma").value;
    var mailformat=/^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$/;
    var nomeformat=/^[a-zA-Z]+$/;
    var cognomeformat=/^[a-zA-Z]+$/;
    var viaformat=/^[a-zA-Z]+$/;
    var capformat=/^[0-9]+$/;
    var bpformat=/^[a-zA-Z]+$/;
    var cittaformat=/^[a-zA-Z]+$/;
    var kindformat=/^[a-zA-Z]+$/;
    if((!mailformat.test(mail)) || (mail == "") || (mail == "undefined")){
      var m=document.getElementById("emaillb");
      m.style.display="block";
      controllo=false;
    }
    if((password == "") || (password == "undefined")){
      m=document.getElementById("passwordlb");
      m.style.display="block";
      controllo=false;
    }
    if((conferma == "") || (conferma == "undefined")||(conerma!=password)){
      m=document.getElementById("passwordlb");
      m.style.display="block";
      controllo=false;
    }
    if ((nome == "") || (nome == "undefined") || (!nomeformat.test(nome))){
      m=document.getElementById("nomelb");
      m.style.display="block";
      controllo=false;
    }
    if ((cognome == "") || (cognome == "undefined") || (!cognomeformat.test(cognome))){
      m=document.getElementById("cognomelb");
      m.style.display="block";
      controllo=false;
    }
    if ((via == "") || (via == "undefined") || (!viaformat.test(via))){
      m=document.getElementById("vialb");
      m.style.display="block";
      controllo=false;
    }
    if ((cap == "") || (cap == "undefined") || (!capformat.test(cap))){
      m=document.getElementById("caplb");
      m.style.display="block";
      controllo=false;
    }
    if ((bp == "") || (bp == "undefined") || (!bpformat.test(bp))){
      m=document.getElementById("bplb");
      m.style.display="block";
      controllo=false;
    }
    if ((citta == "") || (citta == "undefined") || (!cittaformat.test(citta))){
      m=document.getElementById("cittalb");
      m.style.display="block";
      controllo=false;
    }
    if ((kind == "") || (kind == "undefined") || (!kindformat.test(kind))){
      m=document.getElementById("kindlb");
      m.style.display="block";
      controllo=false;
    }
    if (bd == ""){
      m=document.getElementById("bdlb");
      m.style.display="block";
      controllo=false;
    }
    return controllo;
  }
</script>
<center>
<div style="text-align: center; width: 70%;">
  <form action="${pageContext.request.contextPath}/SignInServlet" method="POST" onSubmit="return controllo()">
    <br/>
    <br/>
    <div class="form-group">
      <input name="name" class="form-control" id="nome" type="text" value="<%=cliente.getName()%>"/><br/>
      <label for="nome"></label>
      <label  id="nomelb" style=" color: red; display:none;">*nome non valido</label>
    </div>
    <div class="form-group">
      <input name="surname" class="form-control" id="cognome" type="text" value="<%=cliente.getSurname()%>"/><br/>
      <label for="cognome"></label>
      <label  id="cognomelb" style=" color: red; display:none;">*cognome non valido</label>
    </div>
    <div class="form-group">
      <input type="text" name = "email" class="form-control" id="email" aria-describedby="emailHelp" value="<%=cliente.getEmail()%>"><br>
      <label for="email"></label>
      <label  id="emaillb" style=" color: red; display:none;">*email non valida</label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="password"><br>
      <label for="password"></label>
      <label style=" color: red; display:none;" id="passwordlb" >*password non valida</label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="conferma"><br>
      <label for="conferma"></label>
      <label style=" color: red; display:none;" id="confermalb" >*conferma password non valida</label>
    </div>
    <div class="form-group">
      <input name="birthplace" class="form-control" id="bp" type="text" value="<%=cliente.getBirthPlace()%>"/><br/>
      <label for="bp"></label>
      <label  id="birthplacelb" style=" color: red; display:none;">*luogo di nascita non valido</label>
    </div>
    <div class="form-group">
      <input name="birthdate" class="form-control" id="bd" type="date" value="<%=cliente.getBirthDate()%>"/><br/>
      <label for="bd"></label>
      <label  id="birthdatelb" style=" color: red; display:none;">*data di nascita non valido</label>
    </div>
    <select name="kind" id="kind" class="form-control">
      <option value="Uomo">uomo</option>
      <option value="Donna">donna</option>
      <option value="Preferisco non specificarlo">preferisco non specificarlo</option>
    </select><br/>
    <label for="kind"></label>
    <label  id="kindlb" style=" color: red; display:none;">*genere non valido</label>
    <div class="form-group">
      <input name="city" class="form-control" id="citta" type="text" value="<%=cliente.getCity()%>"/><br/>
      <label for="citta"></label>
      <label  id="citylb" style=" color: red; display:none;">*citta non valido</label>
    </div>
    <div class="form-group">
      <input name="pc" class="form-control" id="cap" type="text" value="<%=cliente.getPc()%>"/><br/>
      <label for="cap"></label>
      <label  id="caplb" style=" color: red; display:none;">*CAP non valido</label>
    </div>
    <div class="form-group">
      <input name="street" class="form-control" id="via" type="text" value="<%=cliente.getStreet()%>"/><br/>
      <label for="via"></label>
      <label  id="vialb" style=" color: red; display:none;">*via non valida</label>
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Aggiorna</button>
    <input type="reset" class="btn btn-primary" value="Cancella">
    <br>
    <br>
  </form>
</div>
</center>
<%@include file="../fragments/footerJSP.jsp"%>
</body>
</html>