<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.easylease.EasyLease.model.order.Order" %>
<%@ page import="com.easylease.EasyLease.model.estimate.Estimate" %>
<%
    List<Object> historyList = (List<Object>) request.getAttribute("historyList");
    List<Order> orderList = new ArrayList<>();
    List<Estimate> estimateList = new ArrayList<>();
    if (historyList == null) {
        response.sendRedirect(request.getContextPath() + "/HistoryClientServlet");
        return;
    }
    Iterator<?> it = historyList.iterator();
    while (it.hasNext()) {
        Object item = it.next();
        if (item instanceof Order) {
            Order ord = (Order) item;
            orderList.add(ord);
        } else if (item instanceof Estimate) {
            Estimate est = (Estimate) item;
            estimateList.add(est);
        }
    }
%>
<html>
<head>
    <title>HistoryClientJSP</title>
    <jsp:include page="/fragments/headerJSP.jsp"></jsp:include>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/historyClientJSP.css"/>
</head>
<body>

<div class="container">
    <div class="table-responsive">
        <table class="table">
            <div id="order_list_text">Ordini</div>
            <thead>
            <tr>
                <th scope="col">Foto</th>
                <th scope="col">Nome</th>
                <th scope="col">Codice</th>
                <th scope="col">Stato</th>
                <th scope="col">Visualizza</th>
            </tr>
            </thead>
            <%
                if (orderList != null && orderList.size() != 0) {
                    for (Order order : orderList) {
            %>
            <tbody>
            <tr>
                <td data-th="Foto">
                    <img src="${pageContext.request.contextPath}/img/<%= order.getEstimate().getCar().getImage() %>">
                </td>
                <td data-th="Nome">
                    <%="" + order.getEstimate().getCar().getBrand() + " " + order.getEstimate().getCar().getModel() %>
                </td>
                <td data-th="Codice">
                    <%=order.getId() %>
                </td>
                <td data-th="Stato">
                    <%=order.getState() %>
                </td>
                <td data-th="Visualizza">
                    <a href="OrderManagementClientServlet?id_order=<%=order.getId()%>"
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Visualizza</a>
                </td>
            </tr>

            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6">Nessun ordinedisponibile</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>


        <table class="table">
            <div id="estimate_list_text">Preventivi</div>
            <thead>
            <tr>
                <th scope="col">Foto</th>
                <th scope="col">Nome</th>
                <th scope="col">Codice</th>
                <th scope="col">Stato</th>
                <th scope="col">Visualizza</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (estimateList != null && estimateList.size() != 0) {
                    for (Estimate estimate : estimateList) {
            %>
            <tr>
                <td data-th="Foto">
                    <img src="${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage() %>">
                </td>
                <td data-th="Nome">
                    <%="" + estimate.getCar().getBrand() + " " + estimate.getCar().getModel() %>
                </td>
                <td data-th="Codice">
                    <%=estimate.getId() %>
                </td>
                <td data-th="Stato">
                    <%=estimate.getState() %>
                </td>
                <td data-th="Visualizza">
                    <a href="EstimateManagementClientServlet?id_estimate=<%=estimate.getId()%>"
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Visualizza</a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6">Nessun preventivo disponibile</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/fragments/footerJSP.jsp"></jsp:include>
</body>
</html>