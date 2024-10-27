<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../navbar.jsp" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x"> 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/basicStyle.css?v=1.x">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablesStyle.css?v=1.x">
    <style type="text/css">
    	h1 {
    		margin-top:20px;
    	}
    	form {
    		padding: 0px;
    	}
    	button, a.button {
    		width: 230px;
    	}
    </style>
    <title>I Miei Treni</title>
</head>
<body>
    <h1>I Miei Treni</h1>

    <!-- Includiamo l'HTML generato dal controller -->
    <div class="container">
        ${trainsTable}
    </div>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>
