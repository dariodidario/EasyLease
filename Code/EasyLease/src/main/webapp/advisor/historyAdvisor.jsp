<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.EasyLease.model.order.Order" %>
<%@ page import="com.easylease.EasyLease.model.estimate.Estimate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%
    if (request.getSession() == null) {
        response.sendRedirect(request.getContextPath() + "/LoginViewServlet");
    }
    List<Object> list = (List<Object>) request.getAttribute("list");
    if (list == null) {
        response.sendRedirect(request.getContextPath() + "/HistoryAdvisorServlet");
        return;
    }
    List<Order> orderList = new ArrayList<>();
    List<Estimate> estimateList = new ArrayList<>();
    for (Object o : list) {
        if (o instanceof Order && ((Order) o).isVisibility()) {
            orderList.add((Order) o);
        } else if (o instanceof Estimate && ((Estimate) o).isVisibility()) {
            estimateList.add((Estimate) o);
        }
    }
%>
<html>
<head>
    <title>HistoryAdvisor</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/advisor/historyAdvisor.css"/>
    <%@include file="/fragments/header.jsp" %>
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
                    Iterator<Order> iterator = orderList.iterator();
                    while (iterator.hasNext()) {
                        Order order = iterator.next();
            %>
            <tbody>
            <tr>
                <td data-th="Foto">
                    <img src="${pageContext.request.contextPath}/img/<%=order.getEstimate().getCar().getImage()%>">
                </td>
                <td data-th="Nome">
                    <%=order.getEstimate().getCar().getBrand() + " " + order.getEstimate().getCar().getModel()%>
                </td>
                <td data-th="Codice"><%=order.getIdOrder()%>
                </td>
                <td data-th="Stato"><%=order.getState() %>
                </td>
                <td data-th="Visualizza">
                    <a href="OrderManagementAdvisorServlet?id_order=<%=order.getIdOrder()%>" id = "<%=order.getIdOrder()%>"
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">
                        <%= order.getState().equals("Pagato") ? "Convalida" : "Visualizza"%>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6">Nessun ordine disponibile</td>
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
                    Iterator<Estimate> iterator = estimateList.iterator();
                    while (iterator.hasNext()) {
                        Estimate estimate = iterator.next();
            %>
            <tr>
                <td data-th="Foto">
                    <img src="${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage()%>">
                </td>
                <td data-th="Nome">
                    <%=estimate.getCar().getBrand() + " " + estimate.getCar().getModel()%>
                </td>
                <td data-th="Codice"><%=estimate.getIdEstimate()%>
                </td>
                <td data-th="Stato"><%=estimate.getState() %>
                </td>
                <td data-th="Visualizza">
                    <a href="EstimateManagementAdvisorServlet?id_estimate=<%=estimate.getIdEstimate()%>" id ="<%=estimate.getIdEstimate()%>"
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">
                        <%= estimate.getState().equals("Richiesto") ? "Prendi in carico" : "Visualizza"%>
                    </a>
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
    <%@include file="/fragments/footer.jsp" %>
</body>
</html>
