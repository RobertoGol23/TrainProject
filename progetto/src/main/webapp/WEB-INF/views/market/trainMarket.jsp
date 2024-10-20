<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import= "org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import= "org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import= "configuration.JpaConfig" %>
<%@ include file="../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Market dei Treni</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        
        h1 {
            color: #8a79c7;
            text-align: center;
            margin: 0;
        }

        .treno {
            background-color: #49456d;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 10px;
            display: flex;
            align-items: center;

            margin-top: 2%;
        }
        .treno img {
            width: 100px;
            height: auto;
            border-radius: 5px;
            margin-right: 10px;
        }


        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            color: #ffffff;
            text-decoration: none;
            padding: 8px 12px;
            background-color: #8a79c7;
            margin: 0 5px;
            border-radius: 5px;
        }
        .pagination a:hover {
            background-color: #79c7e3;
        }
        
        .buttons {
            margin-top: 10px;
            display: flex; /* Imposta il contenitore come Flexbox */
            justify-content: space-between; /* Spazia i pulsanti uniformemente */
        }
        .buttons button {
            width: 100%; /* Larghezza del pulsante (considerando uno spazio tra di essi) */
            padding: 10px;
            background-color: #79c7e3;
            border: none;
            border-radius: 5px;
            color: #2e2b4f;
            cursor: pointer;
        }
        .buttons button:hover {
            background-color: #8a79c7;
            color: #ffffff;
            transition: background-color 0.3s, transform 0.2s; /* Transizione per effetto hover */
        }

    </style>
</head>
<body>

<h1 style="margin-top: 2%; font: size 4.0rem;"> <b> Market dei Treni </b></h1>

<% 
List<Treno> treni = (List<Treno>) session.getAttribute("treni"); 
%>

<% if (treni != null && !treni.isEmpty()) { %>
	<% AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	VotoDAO votoDAO = context.getBean(VotoDAO.class);%>
    <% for (Treno treno : treni) { %>

        <div class="treno">

            <!-- TODO: non funziona l'immagine-->
            <img src="<%= (treno != null && treno.getImageUrl() != null) ? treno.getImageUrl() : "default-image.png" %>" alt="<%= (treno != null) ? treno.getNome() : "Treno non disponibile" %>">
            
            <div>
                <h3><%= (treno != null) ? treno.getNome() : "Treno non disponibile" %></h3>
                <p>Marca: <%= (treno != null) ? treno.getMarca() : "N/A" %></p>
                <p>Prezzo: <%= (treno != null) ? treno.getPrezzoTotaleTreno() : 0 %> euro</p>
                <p>Peso: <%= (treno != null) ? treno.getPesoTotaleTreno() : 0 %> tonnellate</p>
                <p>Peso Trasportabile: <%= (treno != null && treno.getLocomotiva() != null) ? treno.getLocomotiva().getPesoTrainabile() : 0 %> tonnellate</p>
                <p>Numero di Persone: <%= (treno != null) ? treno.getPasseggeriTotali() : 0 %></p>
                <p>Voto: <%= (treno != null) ? votoDAO.calcolaMediaPunteggioTreno(treno.getId()) : 0 %></p>
                
                <!-- Sezione dei bottoni -->
                <div class="buttons">
                    <!-- Bottone per acquistare -->
                    <form method="get" action="purchaseTrain" style="display:inline;">
                        <input type="hidden" name="trenoId" value="<%= (treno != null) ? treno.getId() : 0 %>">
                        <button type="submit">Acquista</button>
                    </form>

                    <!-- Bottone per i dettagli -->
                    <form method="get" action="trainDetails" style="display:inline;">
                        <input type="hidden" name="trenoId" value="<%= (treno != null) ? treno.getId() : 0 %>">
                        <button type="submit">Dettagli</button>
                    </form>

                    <!-- Bottone per votare il treno -->
                    <form method="get" action="voteTrain" style="display:inline;">
                        <input type="hidden" name="trenoId" value="<%= (treno != null) ? treno.getId() : 0 %>">
                        <button type="submit">Vota</button>
                    </form>
                </div>

            </div>
        </div>
    <% } %>
    <% context.close(); %>
<% } else { %>
    <p>Nessun treno disponibile al momento.</p>
<% } %>

<div class="pagination">
    <% 
        Integer currentPage = (Integer) session.getAttribute("currentPage");
        Integer totalPages = (Integer) session.getAttribute("totalPages");
        
        if (currentPage == null) currentPage = 1;
        if (totalPages == null) totalPages = 1;
    %>
    
    <% for (int i = 1; i <= totalPages; i++) { %>
        <% if (i == currentPage) { %>
            <strong><%= i %></strong>
        <% } else { %>
            <a href="trainMarket?page=<%= i %>"><%= i %></a>
        <% } %>
    <% } %>
</div>

</body>
</html>