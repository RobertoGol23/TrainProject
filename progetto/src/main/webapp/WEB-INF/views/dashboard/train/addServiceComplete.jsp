<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>



<!DOCTYPE html>
<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Servizio Aggiunto</title>
    <style>
                
        .container {
            background-color: #49456d;
            padding: 30px;
            border-radius: 10px;
            max-width: 400px;
            margin: auto;
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
		    margin: 0 auto; /* Centra orizzontalmente */
		}
        
        a.button {
        	margin-top: 10px;
			text-align: center;
		    width: 100%;
		    background-color: #f5835e;
		    border: none;
		    border-radius: 5px;
		    color: #2e2b4f;
		    text-align: center;
		    cursor: pointer;
			transition: background-color 0.3s, transform 0.3s; /* Transizione */
		}
		a.button:hover {
		    background-color: #f96737;
		    text-decoration: none;
		    color: #ffffff;
			transform: translateY(-2px); /* Effetto sollevato */
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
        <p>Puoi tornare alla dashboard o continuare a modificare.</p>
        <form action="viewTrain" method="get" class="form-button">
 			<input type="hidden" name="idTreno" value="${idTreno}">
            <button type="submit">Continua a modificare</button>
            <a href="/train-bazaar/dashboard/home" class="button"> Torna alla dashboard </a>
        </form>
    </div>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>