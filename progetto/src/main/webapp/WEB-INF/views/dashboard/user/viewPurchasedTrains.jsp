<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablesStyle.css?v=1.x">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    
	<style type="text/css">
		h1 {
    		margin-top:20px;
    	}
	</style>

    <title>Treni Acquistati</title>
    
</head>
<body>

    <h1>I Tuoi Treni Acquistati</h1>
    <div class="container">
    <table>
        <thead>
            <tr>
                <th>Nome Treno</th>
                <th>Prezzo (€)</th>
                <th>Peso Totale (ton)</th>
                <th>Marca</th>
                <th>Data Acquisto</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<entity.acquisto.Acquisto> acquisti = (List<entity.acquisto.Acquisto>) request.getAttribute("acquisti");
                if (acquisti != null && !acquisti.isEmpty()) {
                    for (entity.acquisto.Acquisto acquisto : acquisti) {
                        entity.treno.Treno treno = acquisto.getTreno();
            %>
                        <tr>
                            <td><%= treno.getNome() %></td>
                            <td><%= treno.getPrezzoTotaleTreno() %></td>
                            <td><%= treno.getPesoTotaleTreno() %></td>
                            <td><%= treno.getMarca() %></td>
                            <td><%= acquisto.getDataAcquisto() != null ? new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(acquisto.getDataAcquisto()) : "N/A" %></td>
                        </tr>
            <% 
                    }
                } else { 
            %>
                <tr>
                    <td colspan="5" class="no-data">Non hai acquistato alcun treno.</td>
                </tr>
            <% } %>
        </tbody>
    </table>
	</div>

   <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->

</body>
</html>
