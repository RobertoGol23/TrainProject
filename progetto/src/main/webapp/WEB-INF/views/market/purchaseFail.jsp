<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Acquisto Fallito</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        .error-message {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            max-width: 600px;
            margin: 20px auto;
            text-align: center;
        }
        a.button {
            background-color: #8a79c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }
        a.button:hover {
            background-color: #79c7e3;
        }
    </style>
</head>
<body>

    <h1>Acquisto Fallito</h1>

    <div class="error-message">
        <p style="color: red;">
            <%-- Mostra il messaggio di errore passato dal controller --%>
            <%= request.getAttribute("error") %>
        </p>
        <a href="/train-bazaar/dashboard/user/wallet" class="button">Aggiungi fondi al tuo wallet</a>
        <a href="/train-bazaar/trainMarket" class="button">Torna al mercato</a>
    </div>

</body>
</html>