<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="../../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Servizio Aggiunto</title>
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
        <h1>Aggiunta Servizio Completata</h1>
        <div class="message">
            <% 
                // Recupera il messaggio dalla richiesta
                String message = (String) request.getAttribute("message");
                if (message != null) { 
            %>
                <p><%= message %></p>
            <% 
                } else { 
            %>
                <p>Il servizio è stato aggiunto con successo!</p>
            <% 
                } 
            %>
        </div>
        <a href="/train-bazaar/dashboard/home"><button>Torna alla Dashboard</button></a>
    </div>

</body>
</html>