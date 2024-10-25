<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">    
    <title>Account Cancellato</title>
    <style>
        
        h1 {
       		margin-bottom: 50px;
       		margin-top: 10px;
       }
       
		h2 {
            margin: 0 0 30px;
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
		<h1>Ci mancherai!</h1>
	    <p>Il tuo account è stato cancellato con successo.</p>
	    <p>Ci piacerebbe rivederti presto, ti invitiamo a iscriverti nuovamente.</p>

	    <!-- TODO: /train-baazar/register -->
	    <form action="/train-bazaar/register" method="get" class="form-button">
	        <button type="submit">Registrati</button>
	    </form>
	</div>

    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>