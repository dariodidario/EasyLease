<%@ page import="com.easylease.EasyLease.model.order.Order" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Order ordine = (Order) request.getAttribute("order");
  if (ordine == null) {
    response.sendRedirect(request.getContextPath() + "/HistoryClientServlet");
    return;
  }
%>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Checkout Ordine</title>
  <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
  />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/client/orderCheckout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
</head>
<body>
<%@include file="/fragments/headerJSP.jsp"%>
<div class="container">
  <div class="row mt-3 mx-2">
    <div class="col-md-6 shadow">
      <div class="row border">
        <div class="col-md-6 text-center border-end">
          <h3 class="text-center">Ordine n: <%=ordine.getId()%></h3>
          <img
              class="mt-3 car__img"
              src="${pageContext.request.contextPath}/img/<%=ordine.getEstimate().getCar().getImage()%>"
          />
          <dl class="row mt-3">
            <dt class="col-4-center">Auto Scelta</dt>
            <dd class="col-4-center"><%=ordine.getEstimate().getCar().getBrand() + ordine.getEstimate().getCar().getModel()%></dd>
          </dl>
        </div>
        <div class="col-md-6 align-self-center">
          <dl class="row">
            <dt class="col-6 text-center">Prima Rata</dt>
            <dd class="col-6 text-center"><%=String.format("%.2f", ordine.getEstimate().getPrice() /
                (ordine.getEstimate().getPeriod() - 1))%> €</dd>
            <dt class="col-6 text-center">Durata</dt>
            <dd class="col-6 text-center"><%=ordine.getEstimate().getPeriod()%> Mesi</dd>
            <dt class="col-6 text-center">Rata Mensile</dt>
            <dd class="col-6 text-center"><%=String.format("%.2f", ordine.getEstimate().getPrice() /
                (ordine.getEstimate().getPeriod()))%> €</dd>
            <dt class="col-6 text-center">Prezzo Totale</dt>
            <dd class="col-6 text-center"><%=String.format("%.2f", ordine.getEstimate().getPrice())%> €</dd>
          </dl>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="row mt-3">
        <div class="col-md-12">
          <div class="table-responsive">
            <table class="table table-bordered shadow">
              <thead>
              <tr>
                <th>Nome Optional</th>
                <th>Tipo</th>
                <th>Prezzo</th>
              </tr>
              </thead>
              <tbody>
              <%
                if (ordine.getEstimate().getOptionalList() != null && ordine.getEstimate().getOptionalList().size() != 0) {
                  Iterator<Optional> it = ordine.getEstimate().getOptionalList().iterator();
                  while (it.hasNext()) {
                    Optional optional = it.next();
              %>
              <tr>
                <td><%=optional.getName()%></td>
                <td><%=optional.getType()%></td>
                <td><%=String.format("%.2f", optional.getPrice() / ordine.getEstimate().getPeriod())%> €/Mese</td>
              </tr>
              <%
                }
              } else {
              %>
              <tr>
                <td colspan="3">Nessun optional selezionato per questo ordine.</td>
              </tr>
              <%
                }
              %>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row mt-3">
    <div class="col-md-3"></div>
    <div class="col-md-6">
      <div class="card__bg">
        <div class="card">

          <form>
            <h1 class="card__title">
              Informazioni Pagamento
            </h1>
            <div class="card__row">
              <div class="card__col">
                <label for="cardNumber"
                       class="card__label">Numero Carta</label>
                <input type="text"
                       size="16"
                       class="card__input card__input--large"
                       id="cardNumber"
                       placeholder="xxxx-xxxx-xxxx-xxxx">
              </div>
            </div>
            <div class="card__row">
              <div class="card__col">
                <label for="cardExpiry" class="card__label">Data Scadenza</label><input type="text" size="8" class="card__input" id="cardExpiry" placeholder="xx/xx"></div>
              <div class="card__col"><label for="cardCcv" class="card__label">CCV</label><input type="text" size="4" class="card__input" id="cardCcv" placeholder="xxx"></div>
              <div class="card__col card__brand"><i id="cardBrand"></i></div>
            </div>
          </form>

        </div>
      </div>
    </div>
    <div class="col-md-3"></div>
    <div class="row mt-3 mb-3">
      <div class="col-md-3"></div>
      <div class="col-md-6 text-center">
        <form action="${pageContext.request.contextPath}/OrderCheckoutServlet" method="post">
          <button id = "payment-submit" type="submit" name = "submit" value="<%=ordine.getId()%>" class="btn btn-primary">Pagamento</button>
        </form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</div>
<div class="col-md-3"></div>
<%@include file="/fragments/footerJSP.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/cleave.js@1.6.0/dist/cleave.min.js"></script>
<script src="${pageContext.request.contextPath}/client/orderCheckout.js"></script>
</body>
</html>
