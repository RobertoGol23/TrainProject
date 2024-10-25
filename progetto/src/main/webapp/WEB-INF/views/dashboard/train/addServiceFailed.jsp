<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>



<!DOCTYPE html>
<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <title>Servizio Non Aggiunto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            text-align: center;
        }
        .container {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            max-width: 400px;
            margin: auto;
            margin-top: 100px;
        }
        h1, h2 {
            margin: 0 0 20px;
        }
        button {
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #79c7e3;
        }
        .message {
            margin-bottom: 20px;
            font-size: 18px;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Aggiunta Servizio Non Riuscita</h1>
        <div class="message">
            <% 
                // Recupera il messaggio dalla richiesta
                String message = (String) request.getAttribute("message");
                if (message != null) { 
            %>
                <p style="color:red"><%= message %></p>
            <% 
                } else { 
            %>
                <p>Non è stato possibile aggiungere il servizio!</p>
            <% 
                } 
            %>
        </div>
        <p>Puoi tornare alla <a href="/train-bazaar/dashboard/home">dashboard</a> o continua a modificare.</p>
        <form action="viewTrain" method="get">
 			<input type="hidden" name="idTreno" value="${idTreno}">
            <button type="submit">Continua a modificare</button>
        </form>
    </div>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>