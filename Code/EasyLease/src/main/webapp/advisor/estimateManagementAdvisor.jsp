<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.easylease.model.estimate.Estimate" %>
<%@ page import="com.easylease.easylease.model.optional.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<%
    if (request.getSession() == null) {
        response.sendRedirect(request.getContextPath() + "/LoginViewServlet");
    }
    Estimate estimate = (Estimate) request.getAttribute("estimate");
    if (estimate == null) {
        response.sendRedirect(request.getContextPath() + "/EstimateManagementAdvisorServlet");
        return;
    }
    ArrayList<Optional> caroptionals = new ArrayList<>();
    ArrayList<Optional> contractoptionals = new ArrayList<>();
    for (Optional o : estimate.getOptionalList()) {
        if (o.getOptionalType().equals("Auto"))
            caroptionals.add(o);
        else if (o.getOptionalType().equals("Contratto"))
            contractoptionals.add(o);
    }

%>
<html>
<head>
    <title>Estimate Management Advisor</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/advisor/estimateManagementAdvisor.css"/>
    <%@include file="/fragments/header.jsp" %>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-6 col-md-6">
            <div class="car_name">
                <%= estimate.getCar().getBrand() + " " + estimate.getCar().getModel()%>
            </div>
            <img src="${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage()%>" class="img-fluid">
        </div>

        <div class="col-12 col-md-6">
            <div class="col" align="center">
                <div class="car_spec_text">
                    Informazioni auto e preventivo
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Porte</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getCar().getDoors()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Cambio</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getCar().getTransmission()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Cavalli</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getCar().getHorsePower()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Classe di emissioni</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getCar().getEmissionClass()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Alimentazione</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getCar().getPowerSupply()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Cilindrata</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getCar().getCapacity()%>
                    </h4>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>ID preventivo</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getIdEstimate()%>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Periodo</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getPeriod()%>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <h4>Stato preventivo</h4>
                </div>
                <div class="col">
                    <h4><%=estimate.getState()%>
                </div>
            </div>
            <div class="col" align="center">
                <div class="car_optional_text">Optional</div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <%
                        if (caroptionals.size() > 0 || contractoptionals.size() > 0)
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
                            if (caroptionals.size() > 0) {
                                Iterator<Optional> carIterator = caroptionals.iterator();
                                while (carIterator.hasNext()) {
                                    Optional carOptional = carIterator.next();
                        %>
                        <td data-th="Nome"><%=carOptional.getOptionalName()%>
                        </td>
                        <td data-th="Costo"><%=carOptional.getPrice()%>
                        </td>
                        <td data-th="Tipo"><%=carOptional.getOptionalType()%>
                        </td>
                    </tr>
                    <tr>
                        <%
                                }
                            }
                            if (contractoptionals.size() > 0) {
                                Iterator<Optional> contractIterator = contractoptionals.iterator();
                                while (contractIterator.hasNext()) {
                                    Optional contractOptional = contractIterator.next();
                        %>
                        <td data-th="Nome"><%=contractOptional.getOptionalName()%>
                        </td>
                        <td data-th="Costo"><%=contractOptional.getPrice()%>
                        </td>
                        <td data-th="Tipo"><%=contractOptional.getOptionalType()%>
                        </td>
                    </tr>
                    </tbody>
                    <%
                        }
                    } else {
                    %>
                    <div class="no_optionals_text" colspan="6"> Nussun optional selezionato</div>
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
                <%
                    if (estimate.getPrice() > 0) {
                %>
                <h2>Prezzo totale</h2>
                <h2 class="price">
                    <%=String.format("%.2f", estimate.getPrice()) + "€"%>
                </h2>
                <h2>Prezzo mensile</h2>
                <h2 class="price">
                    <%=String.format("%.2f", estimate.getPrice() / estimate.getPeriod()) + "€"%>
                </h2>
                <%
                } else {
                %>
                <h2>Prezzo non disponibile</h2>
                <%
                    }
                %>
            </div>
            <%
                if (estimate.getState().equals("Preso in carico")) {
            %>
            <div class="row">
                <div class="col-12">
                    <a href="EstimateStipulationViewServlet?id=<%=estimate.getIdEstimate()%>"
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Stipula</a>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
    <%@include file="/fragments/footer.jsp" %>
</body>
</html>
