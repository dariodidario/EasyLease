<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.easylease.EasyLease.model.estimate.Estimate" %>
<%@ page import="com.easylease.EasyLease.model.optional.Optional" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/estimateManagementClientJSP.css"/>
</head>
<body>
<div class="container w-full">
    <div class="row">
        <div class="col-6" align="center">
            <div class="car_name">
                <%= "" + estimate.getCar().getBrand() + " " + estimate.getCar().getModel()%>
            </div>
        </div>
        <div class="col-6" align="center">
            <div class="car_spec_text">
                Informazioni auto
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col me-6">
            <img src="${pageContext.request.contextPath}/img/<%=estimate.getCar().getImage()%>">
        </div>
        <div class="col ms-6" align="center">
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
            <div class="car_optional_text"> Optionals</div>
            <table class="car_optionals">
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

    <div class="row">
        <div class="col-md-6" align="center">
            <div class="order_status">
                <h2>Prezzo</h2>
                <h2 class="price"><%= estimate.getPrice() != 0 ?
                        String.format("%.2f" , estimate.getPrice() / estimate.getPeriod()) + "€" : "Prezzo non disponibile"%>
                </h2>
                <h2>al mese</h2>
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
                       class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Non confermare</a>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</div>
</body>
</html>
