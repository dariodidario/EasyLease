<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous">
<link href="login.css" rel="stylesheet" type="text/css">
<head>
  <meta charset="ISO-8859-1">
  <title>Login</title>
  <style>
    .btn-primary{
      background-color: #800000 !important;
      border: none !important;
    }

    html,body{
      height:100%;
    }

    div#footer{
      bottom:0;
      height:100px;
      position:absolute;
      width:100%;
      text-align:center;
    }
  </style>
</head>
<body>
<%@include file="../fragments/headerJSP.jsp"%>
<br/>
<script type="text/javascript">
  function controllo(){
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var mailformat=/^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$/;
    var controllo;
    controllo = true;
    if((!mailformat.test(email)) || (email == "") || (email == "undefined")){
      var m=document.getElementById("emaillb");
      m.style.display="block";
      controllo=false;
    }
    if((password == "") || (password == "undefined")){
      m=document.getElementById("passwordlb");
      m.style.display="block";
      controllo=false;
    }
    return controllo;
  }
</script>
<center>
  <div style="text-align: center; width: 30%;">
<form action="${pageContext.request.contextPath}/LoginServlet" method="post" onSubmit="return controllo()">
  <div class="form-group">

    <input type="text" name="userEmail" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
    <label for="email"/><br>
    <label  id="emaillb" style=" color: red; display:none;">*email non valida</label>
  </div>
  <div class="form-group" >
    <input type="password" class="form-control" id="password" placeholder="Password" name="userPassword">
    <label for="password"></label><br>
    <label style=" color: red; display:none;" id="passwordlb" >*password non valida</label>
  </div> <br>
  <a href="${pageContext.request.contextPath}/client/signInJSP.jsp">Registrati</a>
  <br><br>
  <button type="submit" class="btn btn-primary">Login</button>
  <input type="reset" class="btn btn-primary" value="Cancella">
</form>
  </div>
</center>

<div id="footer">
<%@include file="../fragments/footerJSP.jsp"%>
</div>
</body>
</html>