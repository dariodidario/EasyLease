<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.easylease.EasyLease.model.estimate.Estimate" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%
    if (request.getSession() == null) {
        response.sendRedirect(request.getContextPath() + "/LoginViewServlet");
    }

    Estimate estimate = (Estimate) request.getAttribute("estimate");
    List<Optional> optionalList = new ArrayList<>();
    List<Optional> carOptionalList = new ArrayList<>();
    List<Optional> contractOptionalList = new ArrayList<>();

    if (estimate == null) {
        response.sendRedirect(request.getContextPath() + "/EstimateManagementClientServlet");
        return;
    }
    if (!(estimate == null)) {
        optionalList = estimate.getOptionalList();
        carOptionalList = new ArrayList<>();
        contractOptionalList = new ArrayList<>();
        for (Optional item : optionalList) {
            if (item.getType().equals("Contratto")) {
                contractOptionalList.add(item);
            }
            if (item.getType().equals("Auto")) {
                carOptionalList.add(item);
            }
        }
    }
%>
<html>
<head>
    <title>EstimateManagementClient</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/estimateManagementClientJSP.css"/>
    <jsp:include page="/fragments/headerJSP.jsp"></jsp:include>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-12 col-md-6">
            <div class="car_name">
                <%= "" + estimate.getCar().getBrand() + " " + estimate.getCar().getModel()%>
            </div>
            <img src="${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage()%>"
                 class="img-fluid" alt="Responsive image">
        </div>

        <div class="col-12 col-md-6">
            <div class="col" align="center">
                <div class="car_spec_text">
                    Informazioni auto
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Porte</h4>
                </div>
                <div class="col">
                    <h4><%= estimate.getCar().getDoors()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Cambio</h4>
                </div>
                <div class="col">
                    <h4><%= estimate.getCar().getTransmission()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Consumo Medio</h4>
                </div>
                <div class="col">
                    <h4><%= estimate.getCar().getAvg_consumption()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Cavalli</h4>
                </div>
                <div class="col">
                    <h4><%= estimate.getCar().getHorse_power()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Classe di Emissione</h4>
                </div>
                <div class="col">
                    <h4><%= estimate.getCar().getEmission_class()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Alimentazione</h4>
                </div>
                <div class="col">
                    <h4><%= estimate.getCar().getPowerSupply()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Cilindrata Motore</h4>
                </div>
                <div class="col">
                    <h4><%= estimate.getCar().getCapacity()%>
                    </h4>
                </div>
            </div>
            <div class="col" align="center">
                <div class="car_optional_text">Optionals</div>
            </div>
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
                            <%=carOptional.getName() %>
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
                            <%=contractOptional.getName() %>
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
    <div class="row justify-content-md-center">
        <div class="col-12" align="center" id="price_section">
            <div class="order_status">
                <h2>Prezzo totale</h2>
                <h2 class="price">
                    <%=String.format("%.2f", estimate.getPrice()) + "€"%>
                </h2>
                <h2>Prezzo mensile</h2>
                <h2 class="price">
                    <%=String.format("%.2f", estimate.getPrice() / estimate.getPeriod()) + "€"%>
                </h2>
            </div>
            <!-- Se lo stato rientra in questi parametri, mostra i tasti-->
            <%
                if (estimate.getState().equals("Stipulato")) {
            %>
            <div class="row">
                <div class="col-12">
                    <a href="ConfirmEstimateServlet?id_estimate=<%=estimate.getId()%>&choice=<%="Confermato"%>"
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Confermare</a>
                    <a href="ConfirmEstimateServlet?id_estimate=<%=estimate.getId()%>&choice=<%="Non confermato"%>"
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Non
                        confermare</a>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</div>
<jsp:include page="/fragments/footerJSP.jsp"></jsp:include>
</body>
</html>
