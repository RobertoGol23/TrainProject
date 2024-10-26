<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <title>Treno Creato con Successo</title>
    <style>
        
        h1 {
            text-align: center;
            margin-bottom: 50px;
       		margin-top: 10px;
        }
        .content {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
            margin: 20px auto;
            width: 20%;
            text-align: center;
            font-size: 16px;
        }
        a {
            color: #79c7e3;
            text-decoration: none;
            margin-top: 20px;
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
    </style>
</head>
<body>

    

    <div class="content">
    	<h1>Treno Creato con Successo!</h1>
        <p>Il tuo treno è stato creato con successo!</p>
        <p>Puoi tornare alla dashboard o creare un altro treno.</p>
        <form action="createTrain" method="get" class="form-button">
            <button type="submit">Crea un altro treno</button>
            <a href="/train-bazaar/dashboard/home" class="button"> Torna alla dashboard </a>
        </form>
    </div>

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>