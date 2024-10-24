<%@ include file="../navbar.jsp" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/images/logo-icon.png" type="image/icon type">
    <title>Errore Voto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            text-align: center;
        }
        .error-message {
            background-color: #ff4d4d;
            color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
        }
        a {
            color: #ffffff;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>

<h1>Errore Voto</h1>

<div class="error-message">
    <p>Hai già votato questo treno!</p>
    <a href="${pageContext.request.contextPath}/trainMarket">Torna al mercato dei treni</a>
</div>

</body>
</html>
