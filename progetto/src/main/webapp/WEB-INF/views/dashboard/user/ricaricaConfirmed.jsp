<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
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
        
        a {
            color: #79c7e3;
            text-decoration: none;
            margin-top: 20px;
        }
        .error {
        	color: #fc032c;
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

    </style>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wallet Aggiornato</title>

</head>
<body>

    

    <div class="content">
        <h1>Wallet Aggiornato!</h1>
        <p>I tuoi fondi sono stati aggiunti con successo al tuo wallet.</p>
    </div>


  <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>