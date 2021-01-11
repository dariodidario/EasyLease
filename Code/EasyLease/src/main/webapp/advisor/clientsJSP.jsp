<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.easylease.EasyLease.model.client.Client" %>
<%@ page import="java.util.Iterator" %>

<%
  Collection<?> clients = (Collection<?>) request.getAttribute("clients");
  if(clients == null) {
    response.sendRedirect(request.getContextPath() + "/ClientsServlet");
    return;
  }
%>
%>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Lista Clienti</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
</head>
<body>

    <div class="container">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <table class="table table-bordered ">
          <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>E-Mail</th>
          </tr>
          </thead>
          <tbody>
          <%
            if (clients.size() != 0) {
              Iterator<?> it = clients.iterator();
              while (it.hasNext()) {
                Client client = (Client) it.next();
          %>
          <tr>
            <td><%=client.getId()%></td>
            <td><%=client.getName()%></td>
            <td><%=client.getSurname()%></td>
            <td><%=client.getEmail()%></td>
          </tr>
          <%
            }
          } else {
          %>
          <tr>
            <td colspan="3">Nessun cliente disponibile.</td>
          </tr>
          <%
            }
          %>
          </tbody>
        </table>
      </div>
      <div class="col-md-3"></div>
    </div>

</body>
</html>
