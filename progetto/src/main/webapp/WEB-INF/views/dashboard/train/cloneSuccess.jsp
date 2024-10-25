<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Clonazione Avvenuta</title>
    <style>
        
        h1, h2 {
            margin: 0 0 30px;
        }

        .message {
            margin-bottom: 20px;
            font-size: 18px;
            font-weight: bold;
        }
        
        
        form, .form-button {
		    background-color: #49456d;
		    padding: 0px;
		    border-radius: 10px;
		    width: 100%;
		    height: auto; /* Permette al contenuto di determinare l'altezza */
		    margin: 0 auto; /* Centra orizzontalmente */
		    font-weight: normal;
		}
        
        .success-message, .error-message {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            width: 20%;
            margin: 20px auto;
            text-align: center;
        }
        
    </style>
</head>
<body>

	<div>
    

    <%
        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");
        
        if (successMessage != null) { 
    %>
        <div class="success-message">
        	<h1>Treno Clonato!</h1>
            <p><%= successMessage %></p>
            <div class="form-button">
            	<a href="/train-bazaar/dashboard/home" class="button">Torna alla dashboard</a>
        	</div>
     	</div>
    <% } else if (errorMessage != null) { %>
        <div class="error-message">
        	<h1>Treno Non Clonato</h1>
            <p style="color: red;"><%= errorMessage %></p>
            <div class="form-button">
            	<a href="/train-bazaar/dashboard/home" class="button">Torna alla dashboard</a>
        	</div>
        </div>
    <% } else { %>
        <p>Nessuna informazione disponibile.</p>
    <% } %>
    
	</div>

   <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>