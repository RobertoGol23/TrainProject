<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <%@ include file="../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Acquisto Fallito</title>
    <style>
        
        .container {
            background-color: #49456d;
            padding: 30px;
            border-radius: 10px;
            width: 25%;
            margin: 20px auto;
            text-align: center;
            margin-top: 100px;
        }
  
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
		    margin-top: 10px;
		    margin: 0 auto; /* Centra orizzontalmente */
		    font-weight: normal;
		}
		
		button, a.button {
			margin-top: 10px;
		}
        
    </style>
</head>
<body>

    <div class="container">
    <h1>Acquisto Fallito</h1>
        <p style="color: red;">
            <%-- Mostra il messaggio di errore passato dal controller --%>
            <%= request.getAttribute("error") %>
        </p>
        <div class="form-button">
        	<a href="/train-bazaar/dashboard/user/wallet" class="button">Aggiungi fondi al tuo wallet</a>
        	<a href="/train-bazaar/trainMarket" class="button">Torna al mercato</a>
    	</div>
    </div>


 <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>