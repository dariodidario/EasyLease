<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.easylease.easylease.model.estimate.Estimate" %>
<%@ page import="com.easylease.easylease.model.optional.Optional" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%
    if (request.getSession() == null) {
        response.sendRedirect(request.getContextPath() + "/LoginViewServlet");
    }
    Estimate estimate = (Estimate) request.getAttribute("estimate");
    if (estimate == null) {
        response.sendRedirect(request.getContextPath() + "/EstimateStipulationViewServlet");
        return;
    }
    List<Optional> optionalList = estimate.getOptionalList();
%>
<html>
<head>
    <title>Estimate Stipulation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/advisor/estimateStipulation.css"/>
</head>
<body>
<%@include file="/fragments/header.jsp" %>
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
                    Informazioni auto
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
            <div class="col" align="center">
                <div class="car_optional_text">Optional</div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <%
                        if (optionalList.size() > 0 || optionalList.size() > 0)
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
                            if (optionalList.size() > 0) {
                                Iterator<Optional> carIterator = optionalList.iterator();
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
                            if (optionalList.size() > 0) {
                                Iterator<Optional> contractIterator = optionalList.iterator();
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
    `
    <div class="row justify-content-md-center">
        <div class="col-12" align="center" id="price_section">
            <div>
                <h2>Prezzo auto mensile</h2>
                <h2 class="price">
                    <%=String.format("%.2f", estimate.getCar().getPrice()) + "€"%>
                </h2>
            </div>
            <div class="row">
                <div class="col-12">
                    <form action="${pageContext.request.contextPath}/EstimateStipulationServlet" method="post">
                        <div class="mb-3">
                            <%
                                if (optionalList != null && optionalList.size() != 0) {
                                  Iterator<Optional> iterator = optionalList.iterator();
                                  while (iterator.hasNext()) {
                                    Optional optional = iterator.next();
                            %>
                            <div class="w-5">
                                <label for="<%=optional.getOptionalName()%>"
                                       class="form-label mt-3"><%=optional.getOptionalName()%>
                                </label>
                                <input type="number" class="form-control px-2" id="<%=optional.getOptionalName()%>"
                                       name="<%=optional.getOptionalName()%>" min="1" required>
                            </div>
                                <%
                                    }
                                  }
                                %>
                            <input type="hidden" value="<%=estimate.getIdEstimate()%>" name="id">
                            <button type="submit" class="btn btn-primary mt-3 btn-lg active" role="button"
                                    aria-pressed="true" id="stipulate">
                                Conferma
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <%@include file="/fragments/footer.jsp" %>
</div>
</body>
</html>