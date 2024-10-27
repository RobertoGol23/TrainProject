<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="entity.user.User" %>
<%@ page import="entity.votazioni.Voto" %>
<%@ page import="entity.dao.VotoDAO" %>
<%@ page import= "org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import= "org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import= "configuration.JpaConfig" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Vota il Treno</title>
    <style>
        
        .rating {
            margin-top: 10px;
        }
        .rating label {
            margin-right: 5px;
        }
        
        .content {
        	width: 25%;
        }
    </style>
</head>
<body>



<% 
Treno treno = (Treno) session.getAttribute("treno");
User user = (User) session.getAttribute("user");
%>

<% if (treno != null) { %>
<% AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	VotoDAO votoDAO = context.getBean(VotoDAO.class);%>
    	<h1>Vota il Treno <br><%= treno.getNome() %></h1>
    <div class="content">
        <p>Marca: <%= treno.getMarca() %></p>
        <p>Prezzo: <%= treno.getPrezzoTotaleTreno() %> euro</p>
        <p>Peso: <%= treno.getPesoTotaleTreno() %> kg</p>
        <p>Numero di Persone: <%= treno.getPasseggeriTotali() %></p>
        <p>Voto: <%= (treno != null) ? votoDAO.getVotazioneMedia(treno.getId()) : 0 %></p>

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



<!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->


</body>
</html>