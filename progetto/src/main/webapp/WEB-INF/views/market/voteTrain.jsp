<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.user.User" %>
<%@ page import="entity.votazioni.Voto" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import= "org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import= "org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import= "configuration.JpaConfig" %>
<%@ include file="../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vota il Treno</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        .treno {
            background-color: #49456d;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 10px;
        }
        .buttons button {
            margin-top: 10px;
        }
        .rating {
            margin-top: 10px;
        }
        .rating label {
            margin-right: 5px;
        }
    </style>
</head>
<body>

<h1>Vota il Treno</h1>

<% 
Treno treno = (Treno) session.getAttribute("treno");
User user = (User) session.getAttribute("user");
%>

<% if (treno != null) { %>
<% AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	VotoDAO votoDAO = context.getBean(VotoDAO.class);%>
    <div class="treno">
        <h3><%= treno.getNome() %></h3>
        <p>Marca: <%= treno.getMarca() %></p>
        <p>Prezzo: <%= treno.getPrezzoTotaleTreno() %> €</p>
        <p>Peso: <%= treno.getPesoTotaleTreno() %> kg</p>
        <p>Numero di Persone: <%= treno.getPasseggeriTotali() %></p>
        <p>Voto: <%= (treno != null) ? votoDAO.calcolaMediaPunteggioTreno(treno.getId()) : 0 %></p>

        <!-- Form per votare il treno -->
        <form method="post" action="submitVote">
            <input type="hidden" name="trenoId" value="<%= treno.getId() %>">
            <input type="hidden" name="userId" value="<%= user.getId_user() %>">

            <div class="rating">
                <label for="punteggio">Vota (0-5):</label>
                <select name="punteggio" id="punteggio" required>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            
            <div class="buttons">
                <button type="submit">Invia Voto</button>
            </div>
        </form>
    </div>
    <% context.close(); %>
<% } else { %>
    <p>Treno non trovato.</p>
<% } %>

</body>
</html>