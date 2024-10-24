<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">

    <title>Treno Creato con Successo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
        }
        h1 {
            color: #8a79c7;
            text-align: center;
        }
        .content {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        a {
            color: #79c7e3;
            text-decoration: none;
            margin-top: 20px;
        }
        button {
            padding: 10px;
            background-color: #79c7e3;
            border: none;
            border-radius: 5px;
            color: #2e2b4f;
            cursor: pointer;
        }
        button:hover {
            background-color: #8a79c7;
        }
    </style>
</head>
<body>

    <h1>Treno Creato con Successo!</h1>

    <div class="content">
        <p>Il tuo treno è stato creato con successo!</p>
        <p>Puoi tornare alla <a href="/train-bazaar/dashboard/home">dashboard</a> o creare un altro treno.</p>
        <form action="createTrain" method="get">
            <button type="submit">Crea un altro treno</button>
        </form>
    </div>


    <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>