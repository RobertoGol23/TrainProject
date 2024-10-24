<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../navbar.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyle.css?v=1.x">
    <title>Acquisto Completato</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #2e2b4f;
            color: #ffffff;
            text-align: center;
        }
        .message {
            background-color: #49456d;
            padding: 20px;
            border-radius: 10px;
            display: inline-block;
            margin: 20px auto;
        }
        .message h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        button {
            background-color: #8a79c7;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin: 10px;
        }
        button:hover {
            background-color: #79c7e3;
        }
        .dashboard-btn {
            background-color: #4caf50;
        }
        .dashboard-btn:hover {
            background-color: #45a049;
        }
        .market-btn {
            background-color: #8a79c7;
        }
        .market-btn:hover {
            background-color: #79c7e3;
        }
    </style>
</head>
<body>

    <div class="message">
        <h1>Acquisto Completato con Successo!</h1>
        <p>Grazie per il tuo acquisto. Cosa desideri fare ora?</p>
        
        <form action="/train-bazaar/dashboard/home" method="get">
            <button type="submit" class="dashboard-btn">Vai alla Dashboard Personale</button>
        </form>
        
        <form action="/train-bazaar/trainMarket" method="get">
            <button type="submit" class="market-btn">Compra altri Treni</button>
        </form>
    </div>


   <!-- TODO: footer -->
    <!-- <footer>
        &copy; 2024 Sistema Treni. Tutti i diritti riservati.
    </footer> -->
    
</body>
</html>