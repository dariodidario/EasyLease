<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="user/Contatti.css">
  <title>Contatti</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/user/contatti.css"/>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container-fluid">
  <div class="container">
    <div>
      <h2 class="text-center mb-5 p-3">Contattaci</h2>
    </div>
    <div class="row">
      <div class="col-md-7 shadow rounded p-5">
        <div class="row">
          <div class="col mb-3">
            <label class="form-label">Nome</label>
            <input type="text" class="form-control" placeholder="Nome">
          </div>
          <div class="col mb-3">
            <label class="form-label">Cognome</label>
            <input type="text" class="form-control" placeholder="Cognome">
          </div>
        </div>
        <div class="mb-3">
          <label class="form-label">Email</label>
          <input type="text" class="form-control" placeholder="nome@email.com">
        </div>
        <div class="mb-3">
          <label class="form-label">Oggetto</label>
          <input type="text" class="form-control" placeholder="Oggetto">
        </div>
        <div class="mb-3">
          <label class="form-label">Messaggio</label>
          <textarea name="" class="form-control" placeholder="Scrivi il tuo messaggio"></textarea>
        </div>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
          Invia
        </button>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">EasyLease</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                Messaggio inviato con successo!
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary"><a href="${pageContext.request.contextPath}/HomePageServlet">Chiudi</a></button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <div class="ml-5">
          <img src="${pageContext.request.contextPath}/img/misc/mail.svg" alt="" class="img-fluid">
        </div>
      </div>
    </div>
  </div>
</div>

<%@include file="/fragments/footer.jsp" %>
</body>
</html>
