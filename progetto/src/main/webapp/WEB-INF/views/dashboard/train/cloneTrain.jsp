<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="utility.TrenoUtility" %>
<%@ page import="entity.dao.TrenoDAO" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import="configuration.JpaConfig" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Clona Treno</title>
    <style>
        
        .clone-train-form {
            background-color: #49456d;
            padding: 30px;
            border-radius: 10px;
            width: 25%;
            margin: 20px auto;
        }
        
        input {
		    width: 100%;
		    padding: 10px;
		    margin-bottom: 10px;
		    font-size: 15px;
		    border: none;
		    border-radius: 5px;
		}
        
        h1 {
        	margin-top: 0px;
        }
        
        h2 {
        	margin-top: 50px;
        }
        
        .text {
        	padding: 20xp;
        }
        
        form {
        	margin-top: 50px;
		    padding: 0px;
		    width: 100%;
		   
		}
        
    </style>
</head>
<body>

    

    <%
        // Recupera il treno dalla richiesta
        Treno treno = (Treno) request.getAttribute("treno");
    	TrenoUtility tu = new TrenoUtility();
        if (treno != null) {
    %>
    <div class="clone-train-form">
    	<h1>Clona Treno</h1>
        <h2>Dettagli Treno Originale</h2>
        <div class="text">
	        <p>Nome Treno Originale: <%= treno.getNome() %></p>
	        <p>Sigla Treno Originale: <%= tu.getSigla(treno) %></p>
	        <p>Marca: <%= treno.getMarca() %></p>
	        <p>Peso Totale: <%= treno.getPesoTotaleTreno() %> tonnellate</p>
	        <p>Prezzo Totale: <%= treno.getPrezzoTotaleTreno() %> euro</p>
		</div>
        <form action="confirmClone" method="post">
            <input type="hidden" name="idTreno" value="<%= treno.getId() %>">
            <label for="nomeNuovo">Nome Nuovo Treno:</label>
            <input type="text" id="nomeNuovo" name="nomeNuovo" required>
            <button type="submit">Clona Treno</button>
        </form>
    </div>
    <%
        } else {
    %>
        <p>Nessun treno trovato.</p>
    <%
        }
    %>

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>