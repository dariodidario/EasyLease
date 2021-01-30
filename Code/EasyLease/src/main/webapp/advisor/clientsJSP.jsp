<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.EasyLease.model.client.Client" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
  Map<Client, Boolean> clients = (HashMap<Client, Boolean>) request.getAttribute("clients");
  if(clients == null) {
    response.sendRedirect(request.getContextPath() + "/ClientsServlet");
    return;
  }
%>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Lista Clienti</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/advisor/clientsJSP.css"/>
</head>
<body>
<%@include file="/fragments/headerJSP.jsp"%>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-auto table-responsive shadow">
      <table class="table table-bordered caption-top">
        <caption>Lista dei Clienti</caption>
        <thead>
        <tr>
          <th class="text-center">ID</th>
          <th class="text-center">Nome</th>
          <th class="text-center">Cognome</th>
          <th class="text-center">E-Mail</th>
          <th class="text-center"></th>
        </tr>
        </thead>
        <tbody>
        <%
          if(clients.size() != 0) {
            for(Map.Entry<Client, Boolean> entry : clients.entrySet()) {
              Client client = entry.getKey();
              boolean visibility = entry.getValue();
        %>
        <tr>
          <td><%=client.getId()%></td>
          <td><%=client.getName()%></td>
          <td><%=client.getSurname()%></td>
          <td><%=client.getEmail()%></td>
          <%
            if (visibility){
          %>
          <td>
            <button type="button" class="btn btn-outline-primary" id="<%=client.getId()%>">
              <a href="HistoryAdvisorClientServlet?id_client=<%=client.getId()%>">Visualizza</a>
            </button>
          </td>
          <%
          }else {
          %>
          <td><button type="button" class="btn btn-outline-primary" disabled>Visualizza</button></td>
          <%
            }
          %>
        </tr>
        <%
          }
        } else {
        %>
        <tr>
          <td colspan="5">Nessun cliente disponibile.</td>
        </tr>
        <%
          }
        %>
        </tbody>
      </table>
    </div>
  </div>
</div>
<%@include file="/fragments/footerJSP.jsp"%>
</body>
</html>