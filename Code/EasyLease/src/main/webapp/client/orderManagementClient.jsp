<%@ page import="com.easylease.easylease.model.order.Order" %>
<%@ page import="com.easylease.easylease.model.optional.Optional" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession() == null) {
        response.sendRedirect(request.getContextPath() + "/LoginViewServlet");
    }

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Order order = (Order) request.getAttribute("order");

    if (order == null) {
        response.sendRedirect(request.getContextPath() + "/OrderManagementClientServlet");
        return;
    }
    List<Optional> optionalList = new ArrayList<>();
    List<Optional> carOptionalList = new ArrayList<>();
    List<Optional> contractOptionalList = new ArrayList<>();
    if (!(order == null)) {
        optionalList = order.getEstimate().getOptionalList();
        for (Optional item : optionalList) {
            if (item.getOptionalType().equals("Contract")) {
                contractOptionalList.add(item);
            }
            if (item.getOptionalType().equals("Car")) {
                carOptionalList.add(item);
            }
        }
    }
%>
<html>
<head>
    <title>OrderManagementClient</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/orderManagementClient.css"/>
    <jsp:include page="/fragments/header.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12 col-md-6">
            <div class="car_name">
                <%= "" + order.getEstimate().getCar().getBrand() + " "
                        + order.getEstimate().getCar().getModel()%>
            </div>
            <img src="${pageContext.request.contextPath}/img/<%=order.getEstimate().getCar().getImage()%>"
                 class="img-fluid" alt="Responsive image">
            <div class="row justify-content-md-center">
                <div class="col" align="center" id="price_section">
                    <div class="img">
                        <!-- Se lo stato rientra in questi parametri, non mostra i tasti -->
                        <div class="order_status">
                            <h2>Prezzo totale</h2>
                            <h2 class="price">
                                <%=String.format("%.2f", order.getEstimate().getPrice()) + "€"%>
                            </h2>
                            <h2>Prezzo mensile</h2>
                            <h2 class="price">
                                <%=String.format("%.2f", order.getEstimate().getPrice() / order.getEstimate().getPeriod()) + "€"%>
                            </h2>
                        </div>
                        <!-- Se lo stato rientra in questi parametri, mostra i tasti-->
                        <%if (order.getState().equals("Attesa")) { %>
                        <div class=”row”>
                            <div class=”col-12”>
                                <a id="btnConfirm"
                                   href="ConfirmOrderServlet?id_order=<%=order.getIdOrder()%>&choice=<%="Confermato"%>"
                                   class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Conferma</a>
                                <a id="btnDeny"
                                   href="ConfirmOrderServlet?id_order=<%=order.getIdOrder()%>&choice=<%="Non confermato"%>"
                                   class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Non
                                    confermare</a>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <%if (order.getState().equals("Confermato")) { %>
                        <div class=”row”>
                            <div class=”col-12”>
                                <a id="#btnPay"
                                   href="ConfirmOrderServlet?id_order=<%=order.getIdOrder()%>&choice=<%="Paga"%>"
                                   class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Paga</a>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-12 col-md-6">
            <div class="col" align="center">
                <div class="car_spec_text">
                    Informazioni auto
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>ID Ordine</h4>
                </div>
                <div class="col">
                    <h4><%= order.getIdOrder()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Cliente</h4>
                </div>
                <div class="col">
                    <h4><%= "" + order.getEstimate().getClient().getFirstName() + " "
                            + order.getEstimate().getClient().getSurname() %>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Data Inizio</h4>
                </div>
                <div class="col">
                    <h4><%=order.getStartDate() != null ?
                            format.format(order.getStartDate()) :
                            "Data non disponibile" %>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Durata</h4>
                </div>
                <div class="col">
                    <h4><%= order.getEstimate().getPeriod() + " mesi"%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Stato</h4>
                </div>
                <div class="col">
                    <h4><%= order.getState()%>
                    </h4>
                </div>
            </div>
            <a id="contract_link" href="${pageContext.request.contextPath}/source/contract.pdf" target="_blank">Visualizza
                l'intero contratto</a>
            <div class="car_optional_text">Optionals</div>
            <div class="table-responsive">
                <table class="table">
                    <%
                        if (carOptionalList.size() != 0 || contractOptionalList.size() != 0) {
                    %>
                    <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Costo</th>
                        <th scope="col">Tipo</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <%
                            if (carOptionalList.size() != 0) {
                                Iterator<?> it = carOptionalList.iterator();
                                while (it.hasNext()) {
                                    Optional carOptional = (Optional) it.next();
                        %>
                        <td data-th="Nome">
                            <%=carOptional.getOptionalName() %>
                        </td>
                        <td data-th="Costo">
                            <%=carOptional.getPrice() %>
                        </td>
                        <td data-th="Tipo">
                            Auto
                        </td>
                    </tr>
                    <tr>
                        <%
                                }
                            }
                            if (contractOptionalList.size() != 0) {
                                Iterator<?> it2 = contractOptionalList.iterator();
                                while (it2.hasNext()) {
                                    Optional contractOptional = (Optional) it2.next();
                        %>
                        <td data-th="Nome">
                            <%=contractOptional.getOptionalName() %>
                        </td>
                        <td data-th="Costo">
                            <%=contractOptional.getPrice() %>
                        </td>
                        <td data-th="Tipo">
                            Contratto
                        </td>
                    </tr>
                    </tbody>
                    <%
                            }
                        }
                    } else {
                    %>
                    <div class="no_optionals_text" colspan="6">Nessun optional selezionato</div>
                    <%
                        }

                    %>
                </table>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/fragments/footer.jsp"></jsp:include>
</body>
</html>