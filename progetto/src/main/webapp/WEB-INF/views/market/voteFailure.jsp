<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <%@ include file="../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Errore Voto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            text-align: center;
        }
        .error-message {
    		color: #ff4c4c; /* rosso brillante per contrasto */
    		margin-top: 20px;
    		padding: 10px;
    		border: 1px solid #ff4c4c; /* bordi per migliorare visibilità */
    		border-radius: 5px;
   		 	background-color: rgba(255, 76, 76, 0.1); /* sfondo semi-trasparente */
    		display: inline-block;
    		text-align: center;
		}
        a {
            color: #79c7e3;
        }
    </style>
</head>
<body>

<h1>Errore Voto</h1>

     <%
        String error = (String) session.getAttribute("errorMessage");
        if (error != null && !error.isEmpty()) {
    %>
        <div class="error-message">
            <p><%= error %></p>
            <a href="${pageContext.request.contextPath}/trainMarket">Torna al mercato dei treni</a>
        </div>
    <%
        }
    %>


</body>
</html>
