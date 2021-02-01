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

    #divCont{
      text-align: center; width: 30%;
    }
    @media screen and (max-width: 600px) {
      #divCont{
        text-align: center; width: 60%;
      }
    }

    html, body {  height: 100%; }

    .container {
      min-height: 100%;
      height: auto !important;
      margin: 0 auto -100px;
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
<%@include file="../fragments/header.jsp"%>
<br/>
<script type="text/javascript">
  function controllo(){
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var mailformat=/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    var controllo;
    document.getElementById("emaillb").style.display="none"
    document.getElementById("passwordlb").style.display="none"
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

    if(controllo==false){
      document.getElementById("contenitore").style.display="none"
      document.getElementById("contenitore2").style.display="none"
    }

    return controllo;
  }
</script>
<center>
  <div class="container" id="divCont">
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post" onSubmit="return controllo()">

      <div id="contenitore2">
        <%if(request.getSession().getAttribute("ok")=="ok"){%>
        <div id="reg" class="alert alert-dark" role="alert">
          Registrazione avvenuta con successo
        </div>
        <%request.getSession().removeAttribute("ok");%>
        <%}%>
      </div>

      <div id="contenitore">
        <%if(request.getSession().getAttribute("errata")=="errata"){%>
        <div id="wrong" class="alert alert-dark" style="color: red !important;" role="alert">
          Email o password errate
        </div>
        <%request.getSession().removeAttribute("errata");%>
        <%}%>
      </div>


      <div class="form-group">

        <input type="text" name="userEmail" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
        <label for="email"></label>
        <label  id="emaillb" style=" color: red; display:none;">*email non valida</label>
      </div>
      <div class="form-group" >
        <input type="password" class="form-control" id="password" placeholder="Password" name="userPassword">
        <label for="password"></label>
        <label style=" color: red; display:none;" id="passwordlb" >*password non valida</label>
      </div>
      <br>
      <a href="${pageContext.request.contextPath}/client/signIn.jsp">Registrati</a>
      <br><br>
      <button type="submit" id="loginButton" class="btn btn-primary">Login</button>
      <input type="reset" id="deleteButton" class="btn btn-primary" value="Cancella">
    </form>
  </div>
</center>

<div id="footer">
<%@include file="../fragments/footer.jsp"%>
</div>
</body>
</html>