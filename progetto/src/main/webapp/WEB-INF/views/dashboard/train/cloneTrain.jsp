<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="entity.treno.Treno" %>
<%@ page import="utility.TrenoUtility" %>
<%@ page import="entity.dao.TrenoDAO" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="org.springframework.context.support.AbstractApplicationContext" %>
<%@ page import="configuration.JpaConfig" %>vbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <title>Clona Treno</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        .clone-train-form {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            max-width: 600px;
            margin: 20px auto;
        }
        h1 {
            text-align: center;
            padding-top: 15px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #8a79c7;
            border-radius: 5px;
            background-color: #6c6991;
            color: white;
        }
        button {
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            margin: 20px auto;
        }
        button:hover {
            background-color: #79c7e3;
        }
    </style>
</head>
<body>

    <h1>Clona Treno</h1>

    <%
        // Recupera il treno dalla richiesta
        Treno treno = (Treno) request.getAttribute("treno");
    	TrenoUtility tu = new TrenoUtility();
        if (treno != null) {
    %>
    <div class="clone-train-form">
        <h2>Dettagli Treno Originale</h2>
        <p>Nome Treno Originale: <%= treno.getNome() %></p>
        <p>Sigla Treno Originale: <%= tu.getSigla(treno) %></p>
        <p>Marca: <%= treno.getMarca() %></p>
        <p>Peso Totale: <%= treno.getPesoTotaleTreno() %> tonnellate</p>
        <p>Prezzo Totale: <%= treno.getPrezzoTotaleTreno() %> euro</p>

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