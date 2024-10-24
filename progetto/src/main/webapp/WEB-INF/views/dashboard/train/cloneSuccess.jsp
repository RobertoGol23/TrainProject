<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>clude file="../../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <title>Clonazione Avvenuta</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        .success-message, .error-message {
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

    <h1>Clonazione Treno</h1>

    <%
        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");
        
        if (successMessage != null) { 
    %>
        <div class="success-message">
            <p style="color: green;"><%= successMessage %></p>
            <a href="/train-bazaar/dashboard/home" class="button">Torna al tuo profilo</a>
        </div>
    <% } else if (errorMessage != null) { %>
        <div class="error-message">
            <p style="color: red;"><%= errorMessage %></p>
            <a href="/train-bazaar/dashboard/home" class="button">Torna al tuo profilo</a>
        </div>
    <% } else { %>
        <p>Nessuna informazione disponibile.</p>
    <% } %>


   <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>